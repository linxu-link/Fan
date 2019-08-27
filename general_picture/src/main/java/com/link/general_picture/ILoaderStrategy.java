package com.link.general_picture;

public interface ILoaderStrategy {

    /**
     * 加载图片
     * @param builder
     */
    void loadImage(ImageLoadBuilder builder);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache();

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache();

}
