package com.link.component_user.data.source.http



/**
 * @author WJ
 * @date 2019-05-30
 *
 * 描述：网络数据源
 */
class HttpDataSourceImpl constructor(private val service: HttpService) : IHttpDataSource {


    companion object {
        @Volatile
        private var instance: HttpDataSourceImpl? = null

        fun getInstance(service: HttpService) =
                instance ?: synchronized(this) {
                    instance ?: HttpDataSourceImpl(service).also { instance = it }
                }
    }


}