package com.link.fan.app.community

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.data.bean.CommunityEntity
import com.link.fan.databinding.LayoutFeedDetailBottomInateractionBinding
import com.link.fan.widgets.dialog.CommentDialog

abstract class ViewHandler(private val mActivity: FragmentActivity) {

    protected val mViewModel: CommunityViewModel = ViewModelProviders.of(mActivity).get(CommunityViewModel::class.java)

    protected lateinit var mCommunityEntity: CommunityEntity
    protected lateinit var mRecyclerView: RecyclerView
    protected lateinit var mInateractionBinding: LayoutFeedDetailBottomInateractionBinding
    protected lateinit var listAdapter: CommunityAdapter
    private lateinit var commentDialog: CommentDialog

    fun bindData(communityEntity: CommunityEntity) {
        mCommunityEntity = communityEntity
        mInateractionBinding.owner = mActivity
        mRecyclerView.itemAnimator=null
    }

}