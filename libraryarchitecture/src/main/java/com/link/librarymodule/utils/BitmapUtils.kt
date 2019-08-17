package com.link.librarymodule.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.Window
import android.view.WindowManager

/**
 * @author WJ
 * @date 2019-08-17
 *
 * 描述：图片处理工具类
 */
class BitmapUtils {

    companion object {


        /**
         * //计算缩放比率
         * val wm=context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
         * val display=wm.defaultDisplay
         * val reqWidth=display.width
         * val reqHeight=display.height
         */
        fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
            val height = options.outHeight
            val width = options.outHeight
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                val halfHeight = height / 2
                val halfWidth = width / 2

                while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2
                }
            }
            return inSampleSize
        }


        //返回缩小后的bitmap
        fun decodeSampleBitmapFromResource(res: Resources, resId: Int, reqHeight: Int, reqWidth: Int): Bitmap {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(res, resId, options)
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
            options.inJustDecodeBounds = false
            return BitmapFactory.decodeResource(res, resId, options)
        }


        //计算缩放比率
        fun calculateInSampleSize(bitmap: Bitmap, reqWidth: Int, reqHeight: Int): Int {
            val height = bitmap.height
            val width = bitmap.width
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                val halfHeight = height / 2
                val halfWidth = width / 2

                while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2
                }
            }
            return inSampleSize
        }

        //返回缩小后的bitmap
        fun decodeBitmapFromBitmap(origin: Bitmap, reqHeight: Int, reqWidth: Int): Bitmap {
            val inSampleSize = calculateInSampleSize(origin, reqWidth, reqHeight)
            if (inSampleSize == 1) {
                return origin
            } else {
                val width = origin.width / inSampleSize
                val height = origin.height / inSampleSize
                return Bitmap.createScaledBitmap(origin, width, height, true)
            }
        }

    }

}