package com.link.component_user.ui.collection

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.link.component_user.data.UserRepository
import com.link.component_user.data.entity.Collection
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils

class CollectionViewModel(repository: UserRepository) : BaseViewModel<UserRepository>(repository) {


    val mCollectionData = MutableLiveData<List<Collection>>()

    private val userEntity: UserEntity? = BmobUser.getCurrentUser(UserEntity::class.java)


    fun getCollectionData() {
        userEntity ?: return
        val query = BmobQuery<Collection>()
        query.addWhereEqualTo("userId", userEntity.objectId)
        query.findObjects(object : FindListener<Collection>() {
            override fun done(list: MutableList<Collection>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {
                        mCollectionData.value = list
                    }
                } else {
                    Log.e("TAG", e.toString());
                    ToastUtils.showLong(e.toString())
                }

            }


        })
    }


}