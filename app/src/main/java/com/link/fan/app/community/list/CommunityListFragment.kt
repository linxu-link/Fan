package com.link.fan.app.community.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.data.InjectorUtils
import com.link.fan.data.bean.CommunityEntity
import com.link.fan.data.bean.TYPE_VIDEO_TEXT
import com.link.fan.widgets.exoplayer.PageListPlayDetector
import com.link.fan.widgets.exoplayer.PageListPlayManager
import com.link.librarymodule.base.BasePageListFragment
import kotlinx.android.synthetic.main.fragment_community_list.*

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-21-20:09
 * email:wujia0916@thundersoft.com
 * description: 社区列表
 */
class CommunityListFragment : BasePageListFragment<CommunityEntity, CommunityListViewModel>() {

    private var mCommunityType = 1

    private lateinit var mPlayDetector: PageListPlayDetector
    private var mShouldPause = true
    private val mCommunityFeedType = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCommunityType = it.getInt(COMMUNITY_TYPE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.mCommunityLiveData.observe(this, Observer {
            submitList(it)
        })
        mPlayDetector = PageListPlayDetector(this, mRecyclerView)
    }


    override fun onRefresh() {
        mViewModel.mDataSource.invalidate()
    }


    override fun createViewModel(): CommunityListViewModel {
        return InjectorUtils.communityViewModelFactory().create(CommunityListViewModel::class.java)
    }

    override fun createPagedListAdapter(): PagedListAdapter<CommunityEntity, out RecyclerView.ViewHolder> {
        return object : CommunityListAdapter(requireContext(), "all") {

            override fun onViewAttachedToWindow2(holder: ViewHolder?) {
                holder?.apply {
                    if (isVideoItem()) {
                        mPlayDetector.addTarget(getListPlayerView())
                    }
                }
            }

            override fun onViewDetachedFromWindow2(holder: ViewHolder?) {
                super.onViewDetachedFromWindow2(holder)
                holder?.apply {
                    mPlayDetector.removeTarget(getListPlayerView())
                }
            }

            override fun onStartFeedDetailActivity(communityEntity: CommunityEntity) {
                mShouldPause = communityEntity.itemType != TYPE_VIDEO_TEXT
            }

            override fun onCurrentListChanged(previousList: PagedList<CommunityEntity>?, currentList: PagedList<CommunityEntity>?) {
                super.onCurrentListChanged(previousList, currentList)
                if (previousList.isNullOrEmpty() || currentList.isNullOrEmpty()) {
                    return
                }

                if (currentList.containsAll(previousList)) {
                    mRecyclerView.scrollToPosition(0)
                }
            }

        }
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            mPlayDetector.onPause()
        } else {
            mPlayDetector.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (mShouldPause) {
            mPlayDetector.onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        mShouldPause = true
        mPlayDetector.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PageListPlayManager.release(mCommunityFeedType)
    }


    companion object {

        private const val COMMUNITY_TYPE = "community_type"

        @JvmStatic
        fun newInstance(communityType: Int) =
                CommunityListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(COMMUNITY_TYPE, communityType)
                    }
                }
    }

}
