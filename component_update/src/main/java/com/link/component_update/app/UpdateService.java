package com.link.component_update.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.link.component_update.data.entity.UpdateEntity;
import com.link.librarymodule.utils.CommonUtil;
import com.link.librarymodule.utils.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public static void startService(Context context) {
        Intent intent = new Intent(context, UpdateService.class);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BmobQuery<UpdateEntity> query = new BmobQuery<>();
        query.addWhereNotEqualTo("versionCode", CommonUtil.getPackageInfo().versionName);
        query.findObjects(new FindListener<UpdateEntity>() {
            @Override
            public void done(List<UpdateEntity> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                        intent.putExtra(UpdateEntity.class.getCanonicalName(), list.get(0));
                        startActivity(intent);
                    }
                } else {
                    ToastUtils.showLong(e.toString());
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}
