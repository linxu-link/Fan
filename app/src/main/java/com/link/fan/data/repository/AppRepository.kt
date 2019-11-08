package com.link.fan.data.repository

import cn.bmob.v3.BmobUser
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.data.bean.BaseEntity
import com.link.fan.data.bean.MenuResult
import com.link.fan.data.repository.source.local.ILocalService
import com.link.fan.data.repository.source.net.INetService
import io.reactivex.Observable

/**
 * author:wujia
 * create:2019-11-05-18:49
 * email:wujia0916@thundersoft.com
 * description:app中数据源的归总处理类。
 * app 的 request 会首先来到这一层，在这里可以选择该request 是使用本地数据源还是网络数据源。
 * 例如：在获得网络数据后，在本地数据源中做缓存，以后请求数据就可以直接选择本地数据源。
 *
 * 如果操作过多，建议按照模块做拆分，一些不需要本地数据源的模块，可以直接省略localService
 */
class AppRepository constructor(
        private val netService: INetService,
        private val localService: ILocalService) : INetService, ILocalService {

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(net: INetService, local: ILocalService) =
                instance ?: synchronized(this) {
                    instance ?: AppRepository(net, local).also {
                        instance = it
                    }
                }
    }

    override fun homeBanner(): Observable<BaseEntity<MenuResult>> {
        return netService.homeBanner()
    }

    override fun today(): Observable<BaseEntity<MenuResult>> {
        return netService.today()
    }

    override fun lastMenu(): Observable<BaseEntity<MenuResult>> {
        return netService.lastMenu()
    }

    override fun home(): Observable<BaseEntity<MenuResult>> {
        return netService.home()
    }

    override fun login(phone: String, smsCode: String, listener: SaveListener<BmobUser>) {
        return netService.login(phone, smsCode, listener)
    }

    override fun getSmsCode(phone: String, listener: QueryListener<Int>) {
        return netService.getSmsCode(phone, listener)
    }

    override fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>> {
        return netService.getGoods()
    }

    override fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>> {
        return netService.getSeconds()
    }


}