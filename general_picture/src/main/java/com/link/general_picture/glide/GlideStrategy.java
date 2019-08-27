package com.link.general_picture.glide;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.link.general_picture.ILoaderStrategy;
import com.link.general_picture.ImageLoadBuilder;


public class GlideStrategy implements ILoaderStrategy {


    @Override
    public void loadImage(ImageLoadBuilder builder) {

        RequestBuilder<Drawable> requestBuilder = null;


        if (!TextUtils.isEmpty(builder.url)) {
            requestBuilder = Glide.with(builder.context).load(builder.url);
        } else if (builder.uri != null) {
            requestBuilder = Glide.with(builder.context).load(builder.uri);
        } else if (builder.drawableResId != 0) {
            requestBuilder = Glide.with(builder.context).load(builder.drawableResId);
        } else if (builder.file != null) {
            requestBuilder = Glide.with(builder.context).load(builder.file);
        }

        if (requestBuilder == null) {
            throw new NullPointerException("please set the image address!");
        }

        //添加图片的请求头，一般用于防盗链
        if (!TextUtils.isEmpty(builder.headerKey) && !TextUtils.isEmpty(builder.headerValue)) {
            GlideUrl glideUrl = new GlideUrl(builder.url, new LazyHeaders.Builder().addHeader(builder.headerKey, builder.headerValue).build());
            requestBuilder = Glide.with(builder.context).load(glideUrl);
        }

        if (builder.placeholderResId != 0) {
            requestBuilder.placeholder(builder.placeholderResId);
        }

        if (builder.errorResId != 0) {
            requestBuilder.error(builder.errorResId);
        }

        requestBuilder.skipMemoryCache(builder.skipMemoryCache);


        if (builder.targetView != null) {
            requestBuilder.into((ImageView) builder.targetView);
        }

    }


    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }
}
