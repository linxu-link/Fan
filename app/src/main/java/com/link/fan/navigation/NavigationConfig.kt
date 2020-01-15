package com.link.fan.navigation

import android.content.res.AssetManager
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.librarymodule.utils.Utils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * author:wujia
 * create:2020-01-12-19:30
 * version: 1.0
 * description: 路由导航配置
 */
class NavigationConfig {


    companion object {

        private var sDestinationConfig: HashMap<String, Destination>? = null

        @JvmStatic
        fun getDestinationConfig(): HashMap<String, Destination>? {
            if (sDestinationConfig.isNullOrEmpty()) {
                val config = parseFile("destination.json")

                val typeToken = object : TypeToken<HashMap<String, Destination>>() {}.type

                sDestinationConfig = Gson().fromJson(config, typeToken)
            }
            return sDestinationConfig
        }

        fun parseFile(fileName: String): String {
            val assets: AssetManager = Utils.getContext().assets
            var `is`: InputStream? = null
            var br: BufferedReader? = null
            val builder = StringBuilder()
            try {
                `is` = assets.open(fileName)
                br = BufferedReader(InputStreamReader(`is`))
                var line: String? = null
                while (br.readLine().also { line = it } != null) {
                    builder.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    `is`?.close()
                    br?.close()
                } catch (e: Exception) {
                }
            }

            return builder.toString()
        }

        private var sNavController: NavController? = null

        fun getActivityNav(): NavController? {
            return sNavController
        }

        fun setActivityNav(navController: NavController) {
            sNavController = navController
        }

    }

}