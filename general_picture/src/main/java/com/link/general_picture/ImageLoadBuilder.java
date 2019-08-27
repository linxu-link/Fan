package com.link.general_picture;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import java.io.File;

public class ImageLoadBuilder {

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

    public ImageLoadBuilder(ILoaderStrategy strategy) {
        mStrategy = strategy;
    }

    public static class Build{

        private int placeholderResId;
        private int errorResId;
        private boolean isCenterCrop;
        private boolean isCenterInside;
        private boolean skipLocalCache; //是否缓存到本地
        private boolean skipNetCache;
        private Bitmap.Config config = Bitmap.Config.RGB_565;
        private int targetWidth;
        private int targetHeight;
        private float bitmapAngle; //圆角角度
        private float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
        private Drawable placeholder;
        private View targetView;//targetView展示图片
        //    public BitmapCallBack callBack;
        private String url;
        private File file;
        private int drawableResId;
        private Uri uri;

        public Build setPlaceholderResId(int placeholderResId) {
            this.placeholderResId = placeholderResId;
            return this;
        }

        public Build setUrl(String url) {
            this.url = url;
            return this;
        }

        public ImageLoadBuilder build(ILoaderStrategy strategy){
            return new ImageLoadBuilder(strategy);
        }

    }

}
