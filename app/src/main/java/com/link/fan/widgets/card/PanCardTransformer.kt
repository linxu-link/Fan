package com.link.fan.widgets.card

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.link.fan.adapters.ICardAdapter
import com.link.fan.adapters.MAX_ELEVATION_FACTOR

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-29-17:30
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
class PanCardTransformer constructor(private var mViewPager: ViewPager, private var mAdapter: ICardAdapter) : ViewPager.OnPageChangeListener, ViewPager.PageTransformer {


    private var mIsScaled: Boolean = false
    private var mLastOffset = 0f

    init {

        mViewPager.addOnPageChangeListener(this)

    }


    public fun enableScale(enable: Boolean) {

        //已经放大时关闭放大
        if (!enable && mIsScaled) {
            val currentCard = mAdapter.getCardView(mViewPager.currentItem) ?: return
            currentCard.animate().scaleX(1f).scaleY(1f)

        } else if (enable && !mIsScaled) {
            //没有放大时开启放大
            val currentCard = mAdapter.getCardView(mViewPager.currentItem) ?: return
            currentCard.animate().scaleX(1.1f).scaleY(1.1f)
        }

        mIsScaled = enable

    }

    override fun onPageScrollStateChanged(state: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        var realCurrentPosition = 0
        var nextPosition = 0
        val cardElevation = mAdapter.getElevation()
        var realOffset = 0f
        val goingLeft = mLastOffset > positionOffset

        if (goingLeft) {
            realCurrentPosition = position + 1
            nextPosition = position
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }

        //防止滑过头了
        if (nextPosition > mAdapter.getItemCount() - 1 || realCurrentPosition > mAdapter.getItemCount() - 1) {
            return
        }

        val currentCard = mAdapter.getCardView(realCurrentPosition) ?: return
        val nextCard = mAdapter.getCardView(nextPosition) ?: return

        if (mIsScaled) {
            currentCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
            currentCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()

            nextCard.scaleX = (1 + 0.1 * realOffset).toFloat()
            nextCard.scaleY = (1 + 0.1 * realOffset).toFloat()
        }
        currentCard.cardElevation = (cardElevation + cardElevation * (MAX_ELEVATION_FACTOR - 1) * (1 - realOffset))
        nextCard.cardElevation = (cardElevation + cardElevation * (MAX_ELEVATION_FACTOR - 1) * realOffset)

        mLastOffset = positionOffset
    }

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transformPage(page: View, position: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}