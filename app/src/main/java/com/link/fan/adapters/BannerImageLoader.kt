package com.link.fan.adapters

import android.content.Context
import android.widget.ImageView
import com.link.general_picture.glide.GlideStrategy
import com.youth.banner.loader.ImageLoader

class BannerImageLoader : ImageLoader() {


    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

        com.link.general_picture.ImageLoader.getInstance().with(context!!)
                .load(path as String)
                .centerCrop(true)
                .centerInside(true)
                .build(GlideStrategy())
                .into(imageView!!)


    }
}