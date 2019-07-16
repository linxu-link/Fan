package com.link.component_main.app.find

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.link.component_main.R
import com.link.libraryflipview.flip.FlipViewController
import com.link.libraryflipview.utils.AphidLog
import com.link.librarymodule.base.BaseFragment

class FindFragment(override var layoutId: Int = 0) : BaseFragment() {

    private lateinit var rootView: FlipViewController

    companion object {
        @JvmStatic
        fun newInstance() =
                FindFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = FlipViewController(context)
        rootView.setAdapter(MyBaseAdapter(context!!))
        return mRootView
    }

    override fun onResume() {
        super.onResume()
        rootView.onResume()
    }

    override fun onPause() {
        super.onPause()
        rootView.onPause()
    }

    private class MyBaseAdapter constructor(context: Context) : BaseAdapter() {

        private val inflater: LayoutInflater

        private val placeholderBitmap: Bitmap

        init {
            inflater = LayoutInflater.from(context)

            //Use a system resource as the placeholder
            placeholderBitmap = BitmapFactory.decodeResource(context.resources, android.R.drawable.dark_header)
        }

        override fun getCount(): Int {
            return Travels.IMG_DESCRIPTIONS.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var layout = convertView

            if (position == 0) {
                if (convertView == null) {
                    layout = inflater.inflate(R.layout.main_item_find_main, null)
                }

            } else {
                if (convertView == null) {
                    layout = inflater.inflate(R.layout.main_item_find_minor, null)
                }

                val data = Travels.IMG_DESCRIPTIONS.get(position)
            }

            return layout!!
        }
    }

}