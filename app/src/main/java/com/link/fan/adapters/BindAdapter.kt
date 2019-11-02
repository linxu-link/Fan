package com.link.fan.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
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

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}