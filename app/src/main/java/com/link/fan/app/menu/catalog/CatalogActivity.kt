package com.link.fan.app.menu.catalog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.link.fan.R
import com.link.fan.app.main.home.HomeViewModel
import com.link.fan.app.search.SearchActivity
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.ActivityCatalogBinding
import com.link.fan.utils.StatusBar
import com.link.librarymodule.utils.CommonUtil
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * author:wujia
 * create:2020-02-02-00:05
 * version: 1.0
 * description:
 */
class CatalogActivity : AppCompatActivity() {

    private lateinit var mLeftAdapter: LeftCatalogAdapter
    private lateinit var mRightAdapter: RightCatalogAdapter

    private val viewModel: CatalogViewModel by viewModels {
        InjectorUtils.homeViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.fitSystemBar(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
                .setContentView<ActivityCatalogBinding>(this, R.layout.activity_catalog)
                .apply {
                    if (CommonUtil.getStatusBarHeight() >= 0) {
                        val layout = FrameLayout.LayoutParams(root.layoutParams)
                        layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
                        root.layoutParams = layout
                    }
                }
        initToolbar()
        initView()
        initData()
    }

    private fun initToolbar() {
        toolbar_title.text = "分类"
        toolbar_left_icon.setOnClickListener {
            onBackPressed()
        }
        toolbar_right_icon.visibility = View.VISIBLE
        toolbar_right_icon.setImageResource(R.drawable.icon_search_black)
        toolbar_right_icon.setOnClickListener {
            SearchActivity.startActivity(this)
        }
    }

    private fun initView() {
        mLeftAdapter = LeftCatalogAdapter(R.layout.item_list_catalog_left, null)
        mRightAdapter = RightCatalogAdapter(R.layout.item_list_catalog_right, null)

        list_tool.adapter = mLeftAdapter

        list_catalog.adapter = mRightAdapter
        list_catalog.layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        list_catalog.addItemDecoration(ItemDecoration(3, 3, 3, 3))

        mLeftAdapter.setOnItemClickListener { _, _, position ->
            mRightAdapter.setNewData(viewModel.mCatalog.value!![position].list)
            for (result in mLeftAdapter.data) {
                result.click = false
            }
            mLeftAdapter.data[position].click = true
            mLeftAdapter.notifyDataSetChanged()
        }
        mRightAdapter.setOnItemClickListener { adapter, view, position -> }

        viewModel.mCatalog.observe(this, Observer {
            mLeftAdapter.setNewData(it)
            mRightAdapter.setNewData(viewModel.mCatalog.value!![0].list)
        })

    }

    private fun initData() {
        viewModel.getCatalog()
        viewModel.getIngredients()
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, CatalogActivity::class.java)
            context.startActivity(intent)
        }

    }
}
