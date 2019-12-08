package com.link.fan.data.repository.source.net

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.R
import com.link.fan.app.main.community.Community
import com.link.fan.data.bean.BaseEntity
import com.link.fan.data.bean.MenuResult
import com.link.librarymodule.utils.Utils
import io.reactivex.Observable

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-18:56
 * email:wujia0916@thundersoft.com
 * description:  网络数据处理类。
 * 如果有需要，可以在这里对网络来源的数据做简单的处理
 */
class NetServiceImpl constructor(private val service: RetrofitHttpService) : INetService {

    override fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>> {
        return service.getSeconds()
    }

    override fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>> {
        return service.getGoods()
    }

    override fun getHomeBanner(): Observable<BaseEntity<MenuResult>> {
        return service.homeBanner()
    }

    override fun getTodayRecommend(): Observable<BaseEntity<MenuResult>> {
        return service.today()
    }

    override fun getLastMenu(): Observable<BaseEntity<MenuResult>> {
        return service.lastMenu()
    }

    override fun getHomeDataList(): Observable<BaseEntity<MenuResult>> {
        return service.home()
    }

    /**
     * 登录，第三方SDK提供
     */
    override fun login(phone: String, smsCode: String, listener: SaveListener<BmobUser>) {
        val user = BmobUser()
        user.mobilePhoneNumber = phone
        user.username = phone
        user.setPassword(phone)
        user.signOrLogin(smsCode, listener)
    }

    /**
     * 获取短信验证码，第三方SDK提供
     */
    override fun getSmsCode(phone: String, listener: QueryListener<Int>) {
        BmobSMS.requestSMSCode(phone, Utils.getContext().resources.getString(R.string.sms_template), listener)
    }

    override fun requestCommunityList(type: Int, listener: FindListener<Community>) {
        val bmobQuery: BmobQuery<Community> = BmobQuery()
        bmobQuery.addWhereEqualTo("type", type)
        bmobQuery.findObjects(listener)
    }

    companion object {
        @Volatile
        private var sInstance: NetServiceImpl? = null

        fun getInstance(service: RetrofitHttpService) =
                sInstance ?: synchronized(this) {
                    sInstance ?: NetServiceImpl(service).also {
                        sInstance = it
                    }
                }
    }
}