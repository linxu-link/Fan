package com.link.component_hotfix;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.io.File;

public class TinkerService extends Service {
    private String mPatchFileDir;
    private String mPatchFile;
    private final int CHECK_PATCH_UPDATE = 0x01;
    private final int DOWNLOAD_PATH = 0x02;
    private final String PATH_DOT = ".apk"; //补丁文件后缀

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHECK_PATCH_UPDATE:
                    checkPatchUpdate();
                    break;
                case DOWNLOAD_PATH:
                    downPatch();
                    break;
                default:
                    break;
            }
        }
    };

    //对外提供启动servcie方法
    public static void runTinkerService(Context context) {
        try {
            Intent intent = new Intent(context, TinkerService.class);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用来与被启动者通信的接口
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(); //初始化Patch文件目录
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.sendEmptyMessage(CHECK_PATCH_UPDATE); //检查是否有补丁
        return START_NOT_STICKY; //START_NOT_STICKY表示service如果被系统回收不重启
    }

    private void checkPatchUpdate() {

//        RetrofitClient.getInstance()
//                .create(HotfixHttpService.class)
//                .getRepireVersionInfo(CommonUtil.getPackageInfo().versionName)
//                .compose(RxUtils.schedulersTransformer())
//                .subscribe((Consumer<HotFixBase>) hotFixBase -> {
//                    if (hotFixBase.getCode() == 1 && hotFixBase.getList() != null && hotFixBase.getList().size() > 0) {
//                        mAppVersion = hotFixBase.getList().get(0);
//                        downPatch(mAppVersion);
//                    }
//                }, throwable -> stopSelf());


    }


    private void downPatch() {
//        //保存的补丁文件命名规则：xxx/1.0.0.1.apk 1.0.0是正常版本 .1是补丁版本号
//        if (null != appVersion) {
//            mPatchFile = mPatchFileDir.concat(appVersion.getVersionCode()).concat(PATH_DOT);
//            //判断该补丁是否存在，如果已经存在，不需要重复下载
//            File patch = new File(mPatchFile);
//            if (patch.exists()) {
//                return;
//            }
//            OkGo.<File>get(appVersion.getDownloadUrl())
//                    .tag(this)
//                    .execute(new FileCallback(mPatchFileDir, appVersion.getVersionCode().concat(PATH_DOT)) {
//                        @Override
//                        public void onStart(Request<File, ? extends Request> request) {
//                            super.onStart(request);
//                        }
//
//                        @Override
//                        public void downloadProgress(Progress progress) {
//
//                        }
//
//                        @Override
//                        public void onSuccess(Response<File> response) {
//                            TinkerManager.loadPatch(mPatchFile);
//                            stopSelf();
//                        }
//
//                        @Override
//                        public void onError(Response<File> response) {
//                            super.onError(response);
//                            stopSelf();
//                        }
//                    });
//
//        }
    }


    public void init() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //sd卡已挂载
            mPatchFileDir = getExternalCacheDir().getAbsolutePath() + "/patch/";
            File patchDir = new File(mPatchFileDir);
            try {
                if (!patchDir.exists()) {
                    patchDir.mkdir();
                }
            } catch (Exception e) {
                e.printStackTrace();
                stopSelf();
            }
        } else {
//            ToastUtils.showShortSafe("SD卡未挂载");
            stopSelf();
        }
    }

}
