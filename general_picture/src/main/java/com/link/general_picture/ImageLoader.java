package com.link.general_picture;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import java.io.File;

public class ImageLoader {


    private ILoaderStrategy mStrategy;

    public int placeholderResId;
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean skipLocalCache; //是否缓存到本地
    public boolean skipNetCache;
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public int targetWidth;
    public int targetHeight;
    public float bitmapAngle; //圆角角度
    public float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeholder;
    public View targetView;//targetView展示图片
    //    public BitmapCallBack callBack;
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;


    private ImageLoader(ILoaderStrategy strategy) {
        mStrategy = strategy;
    }

    public static class Build{

        public int placeholderResId;
        public int errorResId;
        public boolean isCenterCrop;
        public boolean isCenterInside;
        public boolean skipLocalCache; //是否缓存到本地
        public boolean skipNetCache;
        public Bitmap.Config config = Bitmap.Config.RGB_565;
        public int targetWidth;
        public int targetHeight;
        public float bitmapAngle; //圆角角度
        public float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
        public Drawable placeholder;
        public View targetView;//targetView展示图片
        //    public BitmapCallBack callBack;
        public String url;
        public File file;
        public int drawableResId;
        public Uri uri;


        public ImageLoader build(ILoaderStrategy strategy){
            return new ImageLoader(strategy);
        }

    }

}
