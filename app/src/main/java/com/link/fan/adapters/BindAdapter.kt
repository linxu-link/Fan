package com.link.fan.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.load.resource.gif.GifDrawableResource
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy


@BindingAdapter("imageUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrEmpty()) {
        ImageLoader.getInstance().with(view.context)
                .load(imageUrl)
                .build(GlideStrategy())
                .into(view)
    }

}

@BindingAdapter("gifUrl")
fun bindGifFromUrl(view: ImageView, drawableId: Int?) {

    drawableId?.let {
        Glide.with(view.context).load(it).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }

        }).into(view)
    }

}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}