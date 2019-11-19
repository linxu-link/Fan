package com.link.fan.adapters

import androidx.cardview.widget.CardView

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-29-17:30
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
const val MAX_ELEVATION_FACTOR = 8

interface ICardAdapter {


    fun getElevation(): Float

    fun getCount(): Int

    fun getCardView(position: Int): CardView?

}