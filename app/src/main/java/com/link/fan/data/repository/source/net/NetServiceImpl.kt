package com.link.fan.data.repository.source.net

//import cn.bmob.v3.BmobSMS
//import cn.bmob.v3.BmobUser
//import cn.bmob.v3.listener.FindListener
//import cn.bmob.v3.listener.QueryListener
//import cn.bmob.v3.listener.SaveListener
import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.data.bean.*
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

    override fun searchByKeyword(keyword: String, num: Int, start: Int, appkey: String): Observable<BaseEntity<BaseResult<List<JuHeMenuResult>>>> {
        return service.searchByKeyword(keyword, num, start, appkey)
    }

    override fun category(appkey: String): Observable<BaseEntity<CategoryResult>> {
        return service.category(appkey)
    }

    override fun searchByCategory(appkey: String, classId: Int, start: Int, num: Int): Observable<BaseEntity<BaseResult<List<JuHeMenuResult>>>> {
        return service.searchByCategory(appkey, classId, start, num)
    }

    override fun searchById(appkey: String, id: Int): Observable<BaseEntity<BaseResult<List<JuHeMenuResult>>>> {
        return service.searchById(appkey, id)
    }


    override fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>> {
        return service.getSeconds()
    }

    override fun getCommunityList(pageCount: Int, feedId: Int, feedType: String, userId: Int):
            Observable<BaseEntity<BaseResult<List<CommunityEntity>>>> {
        return service.getCommunityList(pageCount, feedId, feedType, userId)
    }

    override fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>> {
        return service.getGoods()
    }

    override fun getHomeBanner(): Observable<BaseEntity<MenuResult>> {
        return service.getHomeBanner()
    }

    override fun getTodayRecommend(): Observable<BaseEntity<MenuResult>> {
        return service.getTodayRecommend()
    }

    override fun getLastMenu(): Observable<BaseEntity<MenuResult>> {
        return service.getLastMenu()
    }

    override fun getHomeDataList(): Observable<BaseEntity<MenuResult>> {
        return service.getHomeDataList()
    }

    /**
     * 登录，第三方SDK提供
     */
//    override fun login(phone: String, smsCode: String, listener: SaveListener<BmobUser>) {
//        val user = BmobUser()
//        user.mobilePhoneNumber = phone
//        user.username = phone
//        user.setPassword(phone)
//        user.signOrLogin(smsCode, listener)
//    }

    /**
     * 获取短信验证码，第三方SDK提供
     */
//    override fun getSmsCode(phone: String, listener: QueryListener<Int>) {
//        BmobSMS.requestSMSCode(phone, Utils.getContext().resources.getString(R.string.sms_template), listener)
//    }

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