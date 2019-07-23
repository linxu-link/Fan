package com.link.fan.tinker;

import android.content.Context;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class TinkerManager {
    private static boolean isInstalled = false;

    private static ApplicationLike mApplicationLike;

    /**
     * 对Tinker的初始化
     *
     * @param applicationLike
     */
    public static void installTinker(ApplicationLike applicationLike) {
        mApplicationLike = applicationLike;
        if (isInstalled) {
            return;
        }
        LoadReporter loadReporter = new DefaultLoadReporter(getApplicationContext());
        PatchReporter patchReporter = new DefaultPatchReporter(getApplicationContext());
        AbstractPatch upgradePatchProcessor = new UpgradePatch();
        TinkerInstaller.install(applicationLike, loadReporter, patchReporter,
                new DefaultPatchListener(getApplicationContext()),
                DefaultTinkerResultService.class,
                upgradePatchProcessor); //完成Tinker初始化
        isInstalled = true;
    }

    //完成Patch文件的加载
    public static void loadPatch(String localPath) {
        if (Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), localPath);
        }
    }

    //加个md5校验规则,防止补丁文件被篡改
    //服务端返回原始文件的md5值，客户端下载patch文件以后，获取patch文件的MD5值与服务端返回的md5值比较是否相等
    public static void loadPatch(String path, String md5Value) {
        if (Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    private static Context getApplicationContext() {
        if (null != mApplicationLike) {
            return mApplicationLike.getApplication();
        }
        return null;
    }

}
