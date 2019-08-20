package com.link.librarymodule.widgets.navgation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.link.librarymodule.R

/**
 * @author WJ
 * @date 2019-07-20
 *
 * 描述：底部NavigationBar
 */
class BottomNavigationBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), RadioGroup.OnCheckedChangeListener {

    private val array= arrayOf(R.id.home,R.id.classification,R.id.find,R.id.mine)


    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigation_bar, null)
        val radioGroup: RadioGroup = rootView.findViewById(R.id.radio_group)
        radioGroup.setOnCheckedChangeListener(this)
        addView(rootView)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (listener!=null){
            listener!!.onItemClickListener(checkedId)
        }
        for (index in array) {
            rootView.findViewById<RadioButton>(index).setTextColor(ContextCompat.getColor(context,R.color.font_color))
        }
       setColor(rootView.findViewById(checkedId))
    }



    private fun setColor(view:TextView){
//        val tabLayout:TabLayout
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