package com.link.fan.app.search.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.link.fan.R
import com.link.fan.app.search.SearchViewModel
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.ActivitySearchDetailBinding
import com.link.fan.utils.StatusBar
import com.link.librarymodule.utils.CommonUtil
import com.link.librarymodule.widgets.tabs.TabLayout
import kotlinx.android.synthetic.main.include_search.*
import kotlinx.android.synthetic.main.include_toolbar_search.*
import kotlinx.coroutines.selects.select

/**
 * author:wujia
 * create:2020-02-01-23:47
 * version: 1.0
 * description:
 */
class SearchDetailActivity : AppCompatActivity() {

    private var mSearchWord: String? = null
    private var mSearchId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivitySearchDetailBinding>(this, R.layout.activity_search_detail)
                .apply {
                    if (CommonUtil.getStatusBarHeight() >= 0) {
                        val layout = FrameLayout.LayoutParams(root.layoutParams)
                        layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                        root.layoutParams = layout
                    }
                }
        getIntentData()
        initToolbar()
        initView(binding)
    }

    private fun getIntentData() {
        mSearchWord = intent.getStringExtra(SEARCH_WORD)
        mSearchId = intent.getStringExtra(SEARCH_ID)
    }

    private fun initToolbar() {
        toolbar_left_icon.setImageResource(R.drawable.ic_arrow_back_black)
        toolbar_left_icon.setOnClickListener {
            onBackPressed()
        }
        iv_right_icon.visibility = View.VISIBLE
        iv_right_icon.setImageResource(R.drawable.icon_search_black)

        mSearchWord?.let {
            et_search_bar.hint = it
        }
    }


    private fun initView(binding: ActivitySearchDetailBinding) {
        val viewPager2 = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewPager2.adapter = object : FragmentStateAdapter(supportFragmentManager, this.lifecycle) {
            override fun createFragment(position: Int): Fragment {
                //TODO
                return SearchDetailFragment.newInstance(mSearchId, mSearchWord)
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager2.currentItem = it.position
                }
            }
        })


        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }

        })

    }


    companion object {

        private const val SEARCH_WORD = "search_word"
        private const val SEARCH_ID = "search_id"

        fun startActivity(context: Context, searchWord: String?, classId: String?) {
            val intent = Intent(context, SearchDetailActivity::class.java)
            intent.putExtra(SEARCH_WORD, searchWord)
            intent.putExtra(SEARCH_ID, classId)
            context.startActivity(intent)
        }

    }
}
