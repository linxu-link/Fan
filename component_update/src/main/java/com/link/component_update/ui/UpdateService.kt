package com.link.component_update.ui

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.link.component_update.data.entity.Update
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.utils.CommonUtil
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.Utils

const val CHECK_UPDATE = 0x1000

const val SAVE_INSTALLATION = 0x1001
/**
 * @author WJ
 * @date 2019-07-20
 *
 * 描述：更新检查的service
 */
class UpdateService : Service() {


    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onCreate() {
        super.onCreate()
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                CHECK_UPDATE -> checkUpdate()
                SAVE_INSTALLATION -> saveInstallation()
                else -> {
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mHandler.sendEmptyMessage(CHECK_UPDATE)
        mHandler.sendEmptyMessage(SAVE_INSTALLATION)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun checkUpdate() {
        val query = BmobQuery<Update>()
        query.addWhereNotEqualTo("versionCode", CommonUtil.getPackageInfo().versionName)
        query.findObjects(object : FindListener<Update>() {
            override fun done(list: List<Update>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {
                        val intent = Intent(Utils.getContext(), UpdateActivity::class.java)
                        intent.putExtra(Update::class.java.canonicalName, list[0])
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                } else {
                    Log.e("TAG", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }
        })
    }

    private fun saveInstallation() {
        val installation = UserEntity.Installation()
        installation.deviceOS = Build.MODEL + "-" + Build.VERSION.SDK_INT
        installation.save(object : SaveListener<String>() {
            override fun done(p0: String?, e: BmobException?) {
                if (e != null) {
                    Log.e("TAG", e.toString())
                }
            }

        })
    }


}
