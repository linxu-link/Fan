package com.link.librarymodule.widgets.recyclerview

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration constructor(val left: Int, val top: Int, val right: Int, val bottom: Int) : RecyclerView.ItemDecoration() {

    fun dip(value: Int): Int = (value * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(dip(left), dip(top), dip(right), dip(bottom))
    }

}