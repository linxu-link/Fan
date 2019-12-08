package com.link.fan.data.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuDetail(
        val id: String,
        val title: String,
        val tags: String,
        val imtro: String,
        val ingredients: String,
        val burden: String,
        val albums: List<String>,
        val steps: List<StepsBean>) : Parcelable