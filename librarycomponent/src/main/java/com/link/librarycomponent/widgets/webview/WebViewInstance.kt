package com.link.librarycomponent.widgets.webview

import android.content.Context

/**
 * @author WJ
 * @date 2019-08-14
 *
 * 描述：控制webview提前初始化的单例类
 */
class WebViewInstance private constructor(val context: Context){

    var webView:X5WebView= X5WebView(context.applicationContext)

    companion object {
            @Volatile
            private var instance: WebViewInstance? = null

            fun getInstance(context: Context) =
                instance ?: synchronized(this) {
                    instance ?: WebViewInstance(context).also {
                        instance = it
                    }
                }
        }






}