package com.link.general_picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import androidx.annotation.IdRes;

import java.io.File;

public class ImageLoadBuilder {

    //加载图片的策略，默认使用Glide
    public ILoaderStrategy strategy;
    public Context context;

    public int placeholderResId;
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean skipMemoryCache; //是否缓存到本地
    public Bitmap.Config config;
    public int targetWidth;
    public int targetHeight;
    public float bitmapAngle; //圆角角度
    public float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeholder;
    public View targetView;//targetView展示图片
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;

    public String headerKey,headerValue;

    public ImageLoadBuilder(ILoaderStrategy strategy, Builder builder) {
        this.strategy = strategy;
        this.context = builder.context;
        this.config = builder.config;
        this.bitmapAngle = builder.bitmapAngle;
        this.degrees = builder.degrees;

        this.isCenterCrop = builder.isCenterCrop;
        this.isCenterInside = builder.isCenterInside;

        this.placeholderResId = builder.placeholderResId;
        this.placeholder = builder.placeholder;
        this.errorResId = builder.errorResId;

        this.skipMemoryCache = builder.skipMemoryCache;

        this.targetHeight = builder.targetHeight;
        this.targetWidth = builder.targetWidth;

        this.uri = builder.uri;
        this.url = builder.url;
        this.drawableResId = builder.drawableResId;
        this.file = builder.file;

        this.headerKey=builder.headerKey;
        this.headerValue=builder.headerValue;

    }



    public void into(View targetView) {
        this.targetView = targetView;

        this.strategy.loadImage(this);
    }

    public static class Builder {
        private Context context;

        private int placeholderResId;
        private int errorResId;
        private boolean isCenterCrop;
        private boolean isCenterInside;
        private boolean skipMemoryCache; //是否缓存到本地
        private Bitmap.Config config = Bitmap.Config.RGB_565;
        private int targetWidth;
        private int targetHeight;
        private float bitmapAngle; //圆角角度
        private float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
        private Drawable placeholder;
        private String url;
        private File file;
        private int drawableResId;
        private Uri uri;
        private String headerKey,headerValue;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPlaceholderResId(int placeholderResId) {
            this.placeholderResId = placeholderResId;
            return this;
        }

        public Builder load(String url) {
            this.url = url;
            return this;
        }

        public Builder load(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder load(int drawableResId) {
            this.drawableResId = drawableResId;
            return this;
        }

        public Builder load(File file) {
            this.file = file;
            return this;
        }

        public Builder config(Bitmap.Config config) {
            this.config = config;
            return this;
        }

        public Builder placeholder(@IdRes int drawableResId) {
            this.placeholderResId = drawableResId;
            return this;
        }

        public Builder placeholder(Drawable drawable) {
            this.placeholder = drawable;
            return this;
        }

        public Builder error(@IdRes int drawableResId) {
            this.errorResId = drawableResId;
            return this;
        }

        public Builder skipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public Builder centerCrop(boolean isCenterCrop) {
            this.isCenterCrop = isCenterCrop;
            return this;
        }

        public Builder centerInside(boolean isCenterInside) {
            this.isCenterInside = isCenterInside;
            return this;
        }

        public Builder targetWidth(int targetWidth) {
            this.targetWidth = targetWidth;
            return this;
        }

        public Builder targetHeight(int targetHeight) {
            this.targetHeight = targetHeight;
            return this;
        }

        public Builder bitmapAngle(float bitmapAngle) {
            this.bitmapAngle = bitmapAngle;
            return this;
        }

        public Builder degrees(float degrees) {
            this.degrees = degrees;
            return this;
        }

        public Builder header(String headerKey,String headerValue){
            this.headerKey=headerKey;
            this.headerValue=headerValue;
            return this;
        }


        public ImageLoadBuilder build(ILoaderStrategy strategy) {
            return new ImageLoadBuilder(strategy, this);
        }

    }

}
