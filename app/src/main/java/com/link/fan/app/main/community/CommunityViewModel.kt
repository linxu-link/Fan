package com.link.fan.app.main.community

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.utils.ToastUtils

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-21-21:07
 * email:wujia0916@thundersoft.com
 * description:  
 */
class CommunityViewModel constructor(private val repository: AppRepository) : ViewModel() {

    val communityLiveData = MutableLiveData<List<Community>>()

    fun requestData(type: Int) {

        repository.requestCommunityList(type, object : FindListener<Community>() {
            override fun done(list: List<Community>?, exception: BmobException?) {
                if (exception == null) {
                    communityLiveData.value = list
                    communityLiveData.value = list
                } else {
                    ToastUtils.showShort(exception.message)
                }
            }

        })


    }


}