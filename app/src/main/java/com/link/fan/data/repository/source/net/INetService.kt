package com.link.fan.data.repository.source.net

import cn.bmob.v3.BmobUser
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.data.bean.BaseEntity
import com.link.fan.data.bean.MenuResult
import io.reactivex.Observable

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-18:52
 * email:wujia0916@thundersoft.com
 * description:  定义网络数据源的操作，如果操作很多的话，建议按照模块做拆分
 */
interface INetService {

    /**
     * 获取首页的轮播图.
     */
    fun homeBanner(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的今日推荐.
     */
    fun today(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的最新菜谱.
     */
    fun lastMenu(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的菜谱列表.
     */
    fun home(): Observable<BaseEntity<MenuResult>>

    /**
     * 登录
     */
    fun login(phone: String, smsCode: String, listener: SaveListener<BmobUser>)

    /**
     * 获取验证码
     */
    fun getSmsCode(phone: String, listener: QueryListener<Int>)

    /**
     * 获取商品数据
     */
    fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>>

    /**
     * 获取秒杀数据
     */
    fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>>
}