package com.link.component_user.app.footprint

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.link.component_user.data.UserRepository
import com.link.component_user.data.entity.FootPrint
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarybase.ToastUtils

class FootPrintViewModel(repository: UserRepository) : BaseViewModel<UserRepository>(repository) {


    val mFootPrintData = MutableLiveData<List<FootPrint>>()

    private val userEntity: UserEntity? = BmobUser.getCurrentUser(UserEntity::class.java)


    fun getFootPrintData() {
        userEntity ?: return
        val query = BmobQuery<FootPrint>()
        query.addWhereEqualTo("userId", userEntity.objectId)
        query.findObjects(object : FindListener<FootPrint>() {
            override fun done(list: MutableList<FootPrint>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {
                        mFootPrintData.value = list
                    }
                } else {
                    Log.e("TAG", e.toString());
                    ToastUtils.showLong(e.toString())
                }

            }


        })
    }


}