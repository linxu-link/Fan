package com.link.general_network.interceptor

import android.content.Context


import com.link.general_network.utils.NetworkUtil

import java.io.IOException

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author WJ
 * @date 2019-07-14
 *
 * 描述：无网络时强制使用缓存
 */
class CacheInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetworkUtil.isNetworkAvailable(context)) {
            val response = chain.proceed(request)
            // read from cache for 60 s
            val maxAge = 60
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
        } else {
            //读取缓存信息
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            val response = chain.proceed(request)
            //set cache times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
        }
    }
}
