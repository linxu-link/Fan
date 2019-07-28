package com.link.component_main.data.source.local

import com.link.librarymodule.BaseApplication
import com.link.librarybase.Utils
import io.reactivex.Observable
import java.io.IOException

class LocalDataSourceImpl : ILocalDataSource {
    override fun getIngredientsData(): Observable<String> {
        var result = ""
        try {
            //获取输入流
            val mAssets = Utils.getContext().getAssets().open("Ingredients.json")
            //获取文件的字节数
            val lenght = mAssets.available()
            //创建byte数组
            val buffer = ByteArray(lenght)
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer)
            mAssets.close()
            result = String(buffer)
            return Observable.just(result)
        } catch (e: IOException) {
            e.printStackTrace()
            return Observable.just(result)
        }
    }

    override fun getCatalogData(): Observable<String> {
        var result = ""
        try {
            //获取输入流
            val mAssets = Utils.getContext().getAssets().open("catelog.json")
            //获取文件的字节数
            val lenght = mAssets.available()
            //创建byte数组
            val buffer = ByteArray(lenght)
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer)
            mAssets.close()
            result = String(buffer)
            return Observable.just(result)
        } catch (e: IOException) {
            e.printStackTrace()
            return Observable.just(result)
        }
    }


}