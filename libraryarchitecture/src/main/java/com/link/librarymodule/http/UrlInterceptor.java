package com.link.librarymodule.http;

import android.text.TextUtils;

import com.link.librarymodule.constant.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：url 拦截器，根据 url_type 使用不同的Base_url
 *
 * @author WJ
 * @date 2019/6/23
 */
public class UrlInterceptor implements Interceptor {

//    private String mHeader;
//    private HashMap<String,String> mHashMap;
//
//    public UrlInterceptor(String header, HashMap<String,String> hashMap) {
//        mHeader=header;
//        mHashMap=hashMap;
//    }

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
//        if (TextUtils.isEmpty(mHeader)||mHashMap.isEmpty()){
//            throw new IllegalArgumentException("please set header and hashMap");
//        }

        //获取原始的originalRequest
        Request originalRequest = chain.request();
        //获取老的url
        HttpUrl oldUrl = originalRequest.url();
        //获取originalRequest的创建者builder
        Request.Builder builder = originalRequest.newBuilder();
        //获取头信息的集合如：manage,mdffx
        List<String> urlNameList = originalRequest.headers("url_type");
        if (urlNameList != null && urlNameList.size() > 0) {
            //删除原有配置中的值,就是namesAndValues集合里的值
            builder.removeHeader("url_type");
            //获取头信息中配置的value,如：manage或者mdffx
            String urlName = urlNameList.get(0);
            HttpUrl baseURL;
            //根据头信息中配置的value,来匹配新的base_url地址
            if ("mock".equals(urlName)) {
                baseURL = HttpUrl.parse(Constant.MOCK_DATA_URL);
            } else if ("juhe".equals(urlName)) {
                baseURL = HttpUrl.parse(Constant.JUHE_DATA_URL);
            }  else {
                baseURL = HttpUrl.parse(Constant.JUHE_DATA_URL);
            }
            //重建新的HttpUrl，需要重新设置的url部分
            HttpUrl newHttpUrl = oldUrl.newBuilder()
                    .scheme(baseURL.scheme())//http协议如：http或者https
                    .host(baseURL.host())//主机地址
                    .port(baseURL.port())//端口
                    .build();
            //获取处理后的新newRequest
            Request newRequest = builder.url(newHttpUrl).build();
            return chain.proceed(newRequest);
        } else {
            return chain.proceed(originalRequest);
        }
    }
}
