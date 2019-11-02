package com.link.fan.data

import android.content.Context

object InjectorUtils {

//    private fun getPlantRepository(context: Context): PlantRepository {
//        return PlantRepository.getInstance(
//                AppDatabase.getInstance(context.applicationContext).plantDao())
//    }


    fun loginViewModelFactory(context:Context):ViewModelFactory{
        return ViewModelFactory()
    }

}