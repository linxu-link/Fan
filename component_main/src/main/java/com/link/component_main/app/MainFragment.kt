package com.link.component_main.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.link.component_main.R
import kotlinx.android.synthetic.main.main_fragment_main.*

class MainFragment : Fragment() {

    companion object {

        const val TAG = "MainFragment"

        @JvmStatic
        fun newInstance() =
                MainFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arrayListOf<String>()
        for (index in 0 until 20) {
            list.add("ele:$index")
        }

//        rv_list.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.main_item_main_layout, list) {
//            override fun convert(helper: BaseViewHolder?, item: String?) {
//                helper!!.setText(R.id.title, item!!)
//            }
//
//        }
//        rv_list.addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))

        viewPager.setAdapter(ContentPagerAdapter())
        tabLayout.setupWithViewPager(viewPager)
    }

    class ContentPagerAdapter : PagerAdapter() {
        private val PAGE_COUNT = 3

        override fun getCount(): Int {
            return PAGE_COUNT
        }

        @NonNull
        override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
            val textView = TextView(container.context)
            textView.text = getPageTitle(position)
            container.addView(textView)
            return textView
        }

        override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
            if ((`object` as View).parent === container) {
                container.removeView(`object` as View)
            }
        }

        override fun isViewFromObject(@NonNull view: View, @NonNull o: Any): Boolean {
            return view === o
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return "Tab-$position"
        }

    }
}