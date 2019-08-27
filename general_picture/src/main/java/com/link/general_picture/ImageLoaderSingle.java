package com.link.general_picture;

/**
 * @author WJ
 * @date 2019-08-27
 *
 * 描述：图片加载框架，单例封装
 */
public class ImageLoaderSingle {

    //加载图片的策略，默认使用Glide
    private static ILoaderStrategy mStrategy;

    private static class Inner {
        private static ImageLoaderSingle sInstance = new ImageLoaderSingle();
    }

    private ImageLoaderSingle() {

    }

    public static ImageLoaderSingle getInstance() {
        return Inner.sInstance;
    }


    //设定加载的策略
    public static void setStrategy(ILoaderStrategy mStrategy) {
        ImageLoaderSingle.mStrategy = mStrategy;
    }

    public ImageLoadBuilder.Build load(String url){
        return new ImageLoadBuilder.Build().setUrl(url);
    }

    public void load(){

    }



}
