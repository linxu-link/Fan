package com.link.fan.app.menu.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.fan.data.bean.CategoryResult
import com.link.librarymodule.utils.Utils
import java.nio.charset.Charset

class CatalogViewModel : ViewModel() {

    var mCatalog = MutableLiveData<List<CategoryResult>>()
    var mIngredients = MutableLiveData<List<CategoryResult>>()


    fun getCatalog() {

        //获取输入流
        val mAssets = Utils.getContext().assets.open("catelog.json")
        //获取文件的字节数
        val lenght = mAssets.available()
        //创建byte数组
        val buffer = ByteArray(lenght)
        //将文件中的数据写入到字节数组中
        mAssets.read(buffer)
        mAssets.close()
        val json:String =buffer.toString(Charset.defaultCharset())
        val data = Gson().fromJson<List<CategoryResult>>(json, object : TypeToken<ArrayList<CategoryResult>>() {}.type)
        mCatalog.value = data
    }

    fun getIngredients() {

        //获取输入流
        val mAssets = Utils.getContext().assets.open("Ingredients.json")
        //获取文件的字节数
        val lenght = mAssets.available()
        //创建byte数组
        val buffer = ByteArray(lenght)
        //将文件中的数据写入到字节数组中
        mAssets.read(buffer)
        mAssets.close()
        val json = buffer.toString(Charset.defaultCharset())
        val data = Gson().fromJson<List<CategoryResult>>(json, object : TypeToken<ArrayList<CategoryResult>>() {}.type)
        mIngredients.value = data

    }
}