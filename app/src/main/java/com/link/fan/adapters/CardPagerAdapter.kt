package com.link.fan.adapters

import android.view.View
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-29-19:23
 *  email:wujia0916@thundersoft.com
 *  description:  
 * <pre>
 */
class CardPagerAdapter : PagerAdapter(), ICardAdapter {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getElevation(): Float {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCardView(position: Int): CardView? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}