package com.link.general_picture;

import android.content.Context;

/**
 * @author WJ
 * @date 2019-08-27
 *
 * 描述：图片加载框架，单例封装
 */
public class ImageLoader {

    private static class Inner {
        private static ImageLoader sInstance = new ImageLoader();
    }

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        return Inner.sInstance;
    }

    public ImageLoadBuilder.Builder with(Context context){
        return new ImageLoadBuilder.Builder(context);
    }



}
