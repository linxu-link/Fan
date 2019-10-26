package com.link.fan.tinker

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log

import java.io.File

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.DownloadFileListener
import cn.bmob.v3.listener.FindListener
import com.link.librarymodule.utils.CommonUtil
/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-09:46
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
class TinkerService : Service() {
    //补丁的文件夹
    private var mPatchFileDir: String? = null
    //补丁的具体路径
    private var mPatchFilePath: String? = null
    //补丁文件后缀
    private val PATH_DOT = ".apk"

    private val CHECK_PATCH_UPDATE = 0x01
    private val DOWNLOAD_PATH = 0x02

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                CHECK_PATCH_UPDATE -> checkPatchUpdate()
                DOWNLOAD_PATH -> downPatch()
                else -> {
                }
            }
        }
    }

    //用来与被启动者通信的接口
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        init() //初始化Patch文件目录
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mHandler.sendEmptyMessage(CHECK_PATCH_UPDATE) //检查是否有补丁
        return START_NOT_STICKY //START_NOT_STICKY表示service如果被系统回收不重启
    }

    private var mHotfixEntity: HotfixEntity? = null

    private fun checkPatchUpdate() {
        val query = BmobQuery<HotfixEntity>()
        query.addWhereEqualTo("versionName", CommonUtil.getPackageInfo().versionName)
        query.findObjects(object : FindListener<HotfixEntity>() {
            override fun done(data: MutableList<HotfixEntity>?, e: BmobException?) {
                if (e == null) {
                    if (data != null) {
                        mHotfixEntity = data[0]
                        mHandler.sendEmptyMessage(DOWNLOAD_PATH) //检查是否有补丁
                    }else{
                        stopSelf()
                    }
                } else {
                    stopSelf()
                }
            }

        })
    }


    private fun downPatch() {
        if (mHotfixEntity == null) {
            return
        }
        mPatchFilePath = mPatchFileDir + mHotfixEntity!!.patchCode + PATH_DOT
        //判断该补丁是否存在，如果已经存在，不需要重复下载
        val patch = File(mPatchFilePath)
        if (patch.exists()) {
            return
        }
        mHotfixEntity!!.patch.download(patch, object : DownloadFileListener() {
            override fun onProgress(p0: Int?, p1: Long) {

            }

            override fun done(savePath: String?, p1: BmobException?) {
                Log.e("TAG", savePath + "-")
                if (!savePath.isNullOrEmpty()) {
                    TinkerManager.loadPatch(savePath)
                }
                stopSelf()
            }

            override fun doneError(code: Int, msg: String?) {
                super.doneError(code, msg)
                stopSelf()
            }

        })

    }


    private fun init() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            //sd卡已挂载
            mPatchFileDir = externalCacheDir!!.absolutePath + "/patch/"
            val patchDir = File(mPatchFileDir)
            try {
                if (!patchDir.exists()) {
                    patchDir.mkdir()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                stopSelf()
            }

        } else {
            //ToastUtils.showShortSafe("SD卡未挂载");
            stopSelf()
        }
    }

    companion object {

        //对外提供启动servcie方法
        fun runTinkerService(context: Context) {
            try {
                val intent = Intent(context, TinkerService::class.java)
                context.startService(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}
