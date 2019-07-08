package com.link.librarymodule.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.link.librarymodule.R
import com.link.librarymodule.utils.dimen
import com.link.librarymodule.utils.px2sp

/**
 * @author WJ
 * @date 2019-06-02
 *
 * 描述：自定义 公告view
 */
class NewsFlipperView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private val viewFlipperView: ViewFlipper

    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_news_flipper, null)
        viewFlipperView = rootView.findViewById(R.id.flipperView)
        viewFlipperView.setInAnimation(context, R.anim.news_bottom_in)
        viewFlipperView.setOutAnimation(context, R.anim.news_bottom_out)
        addView(rootView)
    }

    fun setData(data: ArrayList<String>) {

        for (text in data) {
            viewFlipperView.addView(buildTextView(text))
        }
        viewFlipperView.startFlipping()

    }

    fun buildTextView(text: String): TextView {

        val textView = TextView(context)
        textView.text = text
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return textView
    }

}