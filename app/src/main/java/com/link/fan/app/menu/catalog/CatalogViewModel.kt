package com.link.fan.app.menu.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.fan.data.bean.CategoryResult
import com.link.librarymodule.utils.Utils
import java.nio.charset.Charset

class CatalogViewModel : ViewModel() {

    var mCategoryLiveData = MutableLiveData<List<CategoryResult>>()

    fun getIngredients() {

        //获取输入流
        val mAssets = Utils.getContext().assets.open("category.json")
        //获取文件的字节数
        val lenght = mAssets.available()
        //创建byte数组
        val buffer = ByteArray(lenght)
        //将文件中的数据写入到字节数组中
        mAssets.read(buffer)
        mAssets.close()
        val json = buffer.toString(Charset.defaultCharset())
        val data = Gson().fromJson<List<CategoryResult>>(json, object : TypeToken<ArrayList<CategoryResult>>() {}.type)
        mCategoryLiveData.value = data

    }
}