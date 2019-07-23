package com.link.component_update.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.link.component_update.data.entity.Update;
import com.link.librarymodule.utils.CommonUtil;
import com.link.librarymodule.utils.ToastUtils;
import com.link.librarymodule.utils.Utils;

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


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BmobQuery<Update> query = new BmobQuery<>();
        query.addWhereNotEqualTo("versionCode", CommonUtil.getPackageInfo().versionName);
        query.findObjects(new FindListener<Update>() {
            @Override
            public void done(List<Update> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        Intent intent = new Intent(Utils.getContext(), UpdateActivity.class);
                        intent.putExtra(Update.class.getCanonicalName(), list.get(0));
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
