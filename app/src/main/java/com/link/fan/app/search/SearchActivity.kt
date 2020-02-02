package com.link.fan.app.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.ActivitySearchBinding
import com.link.fan.utils.StatusBar
import com.link.librarymodule.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.include_search.*
import kotlinx.android.synthetic.main.include_toolbar_search.*

/**
 * author:wujia
 * create:2020-02-01-22:43
 * version: 1.0
 * description:
 */
class SearchActivity : AppCompatActivity() {

    private lateinit var mAdapter: SearchAdapter

    private val mViewModel: SearchViewModel by viewModels {
        InjectorUtils.searchViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivitySearchBinding>(this, R.layout.activity_search)
                .apply {
                    if (CommonUtil.getStatusBarHeight() >= 0) {
                        val layout = FrameLayout.LayoutParams(root.layoutParams)
                        layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                        root.layoutParams = layout
                    }
                }

        initToolbar()
        initView()
        initHeaderView()
        initViewObservable()
        loadData()
    }

    private fun initToolbar() {
        toolbar_left_icon.setImageResource(R.drawable.ic_arrow_back_black)
        toolbar_left_icon.setOnClickListener {
            onBackPressed()
        }
        iv_right_icon.visibility = View.VISIBLE
        iv_right_icon.setImageResource(R.drawable.icon_search_black)
        iv_right_icon.setOnClickListener {
            SearchDetailActivity.startActivity(this)
        }
    }

    private fun initView() {
        refresh.isEnabled = false
        mAdapter = SearchAdapter(android.R.layout.simple_list_item_activated_1, null)
        rvList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvList.adapter = mAdapter


        toolbar_btn_right.setOnClickListener {
            val menu: String = et_search_bar.text.toString()
            if (menu.isNotEmpty()) {
                mViewModel.searchWord.value = menu
            }
        }

        mAdapter.setOnItemClickListener { _, _, position ->
            mViewModel.searchWord.value = mAdapter.getItem(position)!!.content
        }

    }

    private fun initHeaderView() {
        val header = LayoutInflater.from(this).inflate(R.layout.item_search_head_view, null)
        header.findViewById<FrameLayout>(R.id.search_recommend_1).setOnClickListener {
            mViewModel.searchWord.value = "宫保鸡丁"
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_2).setOnClickListener {
            mViewModel.searchWord.value = "糖醋排骨"
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_3).setOnClickListener {
            mViewModel.searchWord.value = "红烧肉"
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_4).setOnClickListener {
            mViewModel.searchWord.value = "水煮肉片"
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_5).setOnClickListener {
            mViewModel.searchWord.value = "麻婆豆腐"
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_6).setOnClickListener {
            mViewModel.searchWord.value = "可乐鸡翅"
        }
        header.findViewById<TextView>(R.id.btn_clear).setOnClickListener {
            mViewModel.clearSearchHistory()
        }

        mAdapter.addHeaderView(header)
    }

    private fun loadData() {
        mViewModel.getSearchHistoryData()
    }

    private fun initViewObservable() {
        mViewModel.searchHistory.observe(this, Observer {
            mAdapter.setNewData(it)
        })

        mViewModel.searchWord.observe(this, Observer {
            mViewModel.insertSearchWord(it)
        })

    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }

    }

}
