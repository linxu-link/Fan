package com.link.component_update

import android.os.Bundle
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.link.component_update.data.entity.UpdateEntity
import com.link.librarycomponent.ServiceFactory
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.utils.ToastUtils

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_activity_main)
//        ServiceFactory.getInstance().updateService!!.startUpdate(this)

        val query = BmobQuery<UpdateEntity>()
        query.addWhereNotEqualTo("versionCode", "1.0.0")
        query.findObjects(object : FindListener<UpdateEntity>() {
            override fun done(list: List<UpdateEntity>, e: BmobException?) {
                if (e == null) {
                    ToastUtils.showLong("xxx")
                } else {
                    ToastUtils.showLong(e.toString())
                }
            }
        })
    }
}