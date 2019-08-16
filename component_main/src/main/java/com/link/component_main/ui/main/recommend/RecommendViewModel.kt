package com.link.component_main.ui.main.recommend

import androidx.lifecycle.MutableLiveData
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.MenuDetail
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RecommendViewModel(repository: MainRepository) : BaseViewModel<MainRepository>(repository) {

    //首页轮播图
    var bannerData = MutableLiveData<List<MenuDetail>>()
    //今日推荐
    var todayData = MutableLiveData<List<MenuDetail>>()
    //更多推荐
    var moreData = MutableLiveData<List<MenuDetail>>()

    //其他页面的主体数据 例如：早餐、中餐等等
    var otherData = MutableLiveData<List<MenuDetail>>()

    /**
     * 获取夏日推荐的数据
     */
    fun getRecommendData() {

        addSubscribe(model.banner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ data ->
                    if (data.resultcode == "200") {
                        bannerData.value = data.result.data
                    }
                }, { t -> ToastUtils.showShort(t.toString()) })
        )

        addSubscribe(model.more()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ data ->
                    if (data.resultcode == "200") {
                        moreData.value = data.result.data
                    }
                }, { t -> ToastUtils.showShort(t.toString()) })
        )

        addSubscribe(model.today()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ data ->
                    if (data.resultcode == "200") {
                        todayData.value = data.result.data
                    }
                }, { t -> ToastUtils.showShort(t.toString()) })
        )
    }

    /**
     * 获取其他推荐的数据
     */
    fun getData(index: Int) {

        var dataSource=model.breakfast()

        when (index) {
            1 -> dataSource=model.breakfast()
            2 -> dataSource=model.launch()
            3 -> dataSource=model.dinner()
            4 -> dataSource=model.motion()
        }

        addSubscribe(dataSource
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ data ->
                    if (data.resultcode == "200") {
                        otherData.value = data.result.data
                    }
                }, { t -> ToastUtils.showShort(t.toString()) })
        )


    }


}