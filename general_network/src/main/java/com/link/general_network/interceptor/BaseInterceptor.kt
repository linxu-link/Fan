package com.link.general_network.interceptor

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author WJ
 * @date 2019-07-14
 * 
 * 描述：
 */
class BaseInterceptor(private val headers: Map<String, String>?) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
                .newBuilder()
        if (headers != null && headers.size > 0) {
            val keys = headers.keys
            for (headerKey in keys) {
                builder.addHeader(headerKey, headers[headerKey]!!).build()
            }
        }
        //请求信息
        return chain.proceed(builder.build())
    }
}