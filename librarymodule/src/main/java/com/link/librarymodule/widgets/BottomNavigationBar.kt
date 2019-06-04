package com.link.librarymodule.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.link.librarymodule.R
import com.link.librarymodule.utils.ToastUtils


class BottomNavigationBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), RadioGroup.OnCheckedChangeListener {

    private val array= arrayOf(R.id.home,R.id.classification,R.id.find,R.id.shopping_cart,R.id.mine)

    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigation_bar, null)
        val gadio_group: RadioGroup = rootView.findViewById(R.id.radio_group)
        gadio_group.setOnCheckedChangeListener(this)
        addView(rootView)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (listener!=null){
            listener!!.onItemClickListener(checkedId)
        }
        for (index in array) {
            rootView.findViewById<RadioButton>(index).setTextColor(ContextCompat.getColor(context,R.color.text_dark))
        }
       setColor(rootView.findViewById(checkedId))
    }

    private fun setColor(view:TextView){
        view.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary))
    }


    private var listener: OnClickListener? = null

    interface OnClickListener {

        fun onItemClickListener(checkedId: Int)

    }

    fun setOnItemClickListener(listener: OnClickListener){
        this.listener=listener
    }

}