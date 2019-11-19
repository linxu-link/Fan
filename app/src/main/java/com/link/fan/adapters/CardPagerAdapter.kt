package com.link.fan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.link.fan.R
import com.link.fan.data.bean.MenuDetail
import com.link.fan.databinding.ListItemCardViewBinding
import com.link.librarymodule.utils.CommonUtil
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.Utils
import kotlin.collections.ArrayList

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

    private var mCardViews = arrayListOf<CardView>()

    private var mDataList = listOf<MenuDetail>()

    private var mBaseElevation = 0f

    fun setDataList(list: List<MenuDetail>) {
        mDataList = list
        for (i in 0..list.size) {
            mCardViews.add(CardView(Utils.getContext()))
        }
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mDataList.size
    }

    override fun getElevation(): Float {
        return mBaseElevation
    }

    override fun getCardView(position: Int): CardView? {
        return mCardViews[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val bind = DataBindingUtil.inflate<ListItemCardViewBinding>(
                LayoutInflater.from(container.context),
                R.layout.list_item_card_view,
                container, false)
        container.addView(bind.root)
        bind.menuDetail = mDataList[position]
        bind.clickListener = View.OnClickListener {
            //TODO
            ToastUtils.showShort("click")
        }

        if (mBaseElevation == 0f) {
            mBaseElevation = bind.cardView.cardElevation
        }

        bind.cardView.maxCardElevation = mBaseElevation * MAX_ELEVATION_FACTOR
        mCardViews[position] = bind.cardView

        return bind.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mCardViews.removeAt(position)
    }
}