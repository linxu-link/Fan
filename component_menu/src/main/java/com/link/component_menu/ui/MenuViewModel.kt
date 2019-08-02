package com.link.component_menu.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import com.link.component_menu.data.MenuRepository
import com.link.component_menu.data.entity.Collection
import com.link.component_menu.data.entity.FootPrint
import com.link.component_menu.data.entity.MenuDetail
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MenuViewModel constructor(repository: MenuRepository) : BaseViewModel<MenuRepository>(repository) {

    val menuDetail = MutableLiveData<MenuDetail>()

    val burden = MutableLiveData<List<String>>()

    val ingredients = MutableLiveData<List<String>>()

    val recommendList = MutableLiveData<List<MenuDetail>>()

    val isCollection = MutableLiveData<Boolean>()

    val userEntity = BmobUser.getCurrentUser(UserEntity::class.java)

    private var objectId = ""

    init {
        isCollection.value = false
    }

    fun getRecommend(menu: String) {

        addSubscribe(
                getModel().getRecommend(menu, 0, 6)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(Consumer {
                            if (it.resultcode == "200") {
                                recommendList.value = it.result.data
                            } else {
//                                ToastUtils.showLong(it.reason)
                            }
                        }, Consumer {

                        })
        )

    }

    //是否收藏
    fun isCollection(menuId: String) {
        userEntity ?: return

        val query = BmobQuery<Collection>()
        query.addWhereEqualTo("userId", userEntity.objectId)
                .addWhereEqualTo("id", menuId)
        query.findObjects(object : FindListener<Collection>() {
            override fun done(list: MutableList<Collection>?, e: BmobException?) {
                if (e == null) {
                    isCollection.value = list != null && list.isNotEmpty()
                    if (list != null && list.isNotEmpty()) {
                        objectId = list[0].objectId
                    }
                } else {
                    Log.e("error", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }

        })

        saveFootPrint(menuId)

    }

    //设定收藏
    fun setCollection() {
        userEntity ?: return

        val menuEntity = menuDetail.value ?: return
        val collection = Collection()

        if (!isCollection.value!!) {
            collection.userId = userEntity.objectId
            collection.id = menuEntity.id
            collection.title = menuEntity.title
            collection.tags = menuEntity.tags
            collection.imtro = menuEntity.imtro
            collection.ingredients = menuEntity.ingredients
            collection.burden = menuEntity.burden
            collection.albums = menuEntity.albums
            collection.steps = menuEntity.steps
            collection.save(object : SaveListener<String>() {
                override fun done(objectId: String?, e: BmobException?) {
                    if (e == null) {
                        this@MenuViewModel.objectId = objectId!!
                        isCollection.value = true
                    } else {
                        Log.e("error", e.toString())
                        ToastUtils.showLong(e.toString())
                    }
                }

            })
        } else {
            collection.objectId = objectId
            collection.delete(object : UpdateListener() {
                override fun done(e: BmobException?) {
                    if (e == null) {
                        isCollection.value = false
                    } else {

                    }
                }

            })
        }

    }

    //保存足迹
    private fun saveFootPrint(menuId: String) {
        userEntity ?: return
        val menuEntity = menuDetail.value ?: return

        val query = BmobQuery<FootPrint>()
        query.addWhereEqualTo("userId", userEntity.objectId).addWhereEqualTo("id", menuId)

        query.findObjects(object : FindListener<FootPrint>() {
            override fun done(list: MutableList<FootPrint>?, e: BmobException?) {
                if (e == null) {
                    if (list.isNullOrEmpty()) {
                        val footPrint = FootPrint()
                        footPrint.userId = userEntity.objectId
                        footPrint.id = menuEntity.id
                        footPrint.title = menuEntity.title
                        footPrint.tags = menuEntity.tags
                        footPrint.imtro = menuEntity.imtro
                        footPrint.ingredients = menuEntity.ingredients
                        footPrint.burden = menuEntity.burden
                        footPrint.albums = menuEntity.albums
                        footPrint.steps = menuEntity.steps
                        footPrint.save(object : SaveListener<String>() {
                            override fun done(objectId: String?, e: BmobException?) {
                                if (e == null) {

                                } else {
                                    Log.e("error", e.toString())
                                    ToastUtils.showLong(e.toString())
                                }
                            }

                        })
                    }
                } else {
                    Log.e("error", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }

        })

    }

}