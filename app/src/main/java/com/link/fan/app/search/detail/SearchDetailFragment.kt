package com.link.fan.app.search.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.link.fan.R
import com.link.fan.data.InjectorUtils
import com.link.fan.databinding.FragmentSearchDetailBinding
import com.link.librarymodule.widgets.recyclerview.ItemDecoration

private const val ARG_ID = "search_id"
private const val ARG_WORD = "search_word"


class SearchDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchDetailBinding
    private var mPosition: Int = 1

    private val mViewModel: SearchDetailViewModel by viewModels {
        InjectorUtils.createViewModel()
    }

    private var mSearchWord: String? = null
    private var mSearchId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mSearchWord = it.getString(ARG_WORD)
            mSearchId = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil
                .inflate<FragmentSearchDetailBinding>(inflater, R.layout.fragment_search_detail, container, false)
                .apply {
                    lifecycleOwner = viewLifecycleOwner
                }
        val adapter = FoodSearchDetailAdapter(R.layout.item_list_search_detail_food, null)
        mBinding.list.addItemDecoration(ItemDecoration(0, 5, 0, 5))
        mBinding.list.adapter = adapter
        mBinding.refresh.setColorSchemeResources(R.color.colorPrimaryDark)
        subscribeUi(adapter)
        initView(adapter)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    private fun initView(adapter: FoodSearchDetailAdapter) {
        mBinding.refresh.setOnRefreshListener {
            mPosition = 1
            requestData()
        }

        adapter.setEnableLoadMore(true)
        adapter.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {

            override fun onLoadMoreRequested() {
                mPosition += 10
                requestData()
            }

        }, mBinding.list)

    }

    private fun requestData() {
        mBinding.refresh.isRefreshing = true
        mSearchWord?.let {
            mViewModel.searchByKeyword(it, mPosition, 10)
        }
        mSearchId?.let {
            mViewModel.searchById(it, mPosition, 10)
        }
    }

    private fun subscribeUi(adapter: FoodSearchDetailAdapter) {
        mViewModel.mSearchData.observe(this, Observer {
            if (mPosition == 1) {
                adapter.setNewData(it)
            } else {
                adapter.addData(it)
            }
            mBinding.refresh.isRefreshing = false
            adapter.loadMoreComplete()

            if (it.isNullOrEmpty() || it.size < 10) {
                adapter.loadMoreEnd(true)
            }
            mBinding.refresh.isRefreshing = false
        })

    }


    companion object {

        @JvmStatic
        fun newInstance(searchId: String?, searchWord: String?) =

                SearchDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_WORD, searchWord)
                        putString(ARG_ID, searchId)
                    }
                }
    }
}
