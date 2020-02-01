package com.link.fan.app.community.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.BR
import com.link.fan.R
import com.link.fan.app.community.detail.CommunityDetailActivity
import com.link.fan.data.bean.CommunityEntity
import com.link.fan.data.bean.TYPE_IMAGE_TEXT
import com.link.fan.data.bean.TYPE_VIDEO_TEXT
import com.link.fan.databinding.ItemListCommunityTypeImageBinding
import com.link.fan.databinding.ItemListCommunityTypeVideoBinding
import com.link.fan.widgets.exoplayer.ListPlayerView
import com.link.librarymodule.base.adapter.BasePagedListAdapter

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-06-11:10
 * email:wujia0916@thundersoft.com
 * description:
 */
open class CommunityListAdapter(val context: Context, val category: String)
    : BasePagedListAdapter<CommunityEntity, CommunityListAdapter.ViewHolder>(

        object : DiffUtil.ItemCallback<CommunityEntity>() {
            override fun areItemsTheSame(oldItem: CommunityEntity, newItem: CommunityEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CommunityEntity, newItem: CommunityEntity): Boolean {
                return oldItem == newItem
            }
        }
) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemViewType2(position: Int): Int {
        getItem(position)?.apply {
            if (itemType == TYPE_IMAGE_TEXT) {
                return R.layout.item_list_community_type_image
            } else if (itemType == TYPE_VIDEO_TEXT) {
                return R.layout.item_list_community_type_video
            }
        }
        return 0
    }


    open fun onStartFeedDetailActivity(communityEntity: CommunityEntity) {

    }

    override fun onCreateViewHolder2(parent: ViewGroup?, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(mInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder2(holder: ViewHolder?, position: Int) {

        val communityEntity = getItem(position)

        holder?.apply {
            communityEntity?.apply {
                bind(communityEntity)

                itemView.setOnClickListener {
                    //TODO
                    CommunityDetailActivity.startActivity(context)
                    onStartFeedDetailActivity(communityEntity)
                }
            }
        }

    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: CommunityEntity) {
            binding.setVariable(BR.community, entity)
            binding.setVariable(BR.lifeCycleOwner, context)

            if (binding is ItemListCommunityTypeImageBinding) {

                binding.image.bindData(entity.width, entity.height, 16, entity.cover)

            } else if (binding is ItemListCommunityTypeVideoBinding) {

                binding.listPlayerView.bindData(category, entity.width, entity.height, entity.cover, entity.url)

            }
        }

        fun isVideoItem(): Boolean {
            return binding is ItemListCommunityTypeVideoBinding
        }

        fun getListPlayerView(): ListPlayerView? {
            if (binding is ItemListCommunityTypeVideoBinding) {
                return binding.listPlayerView
            } else {
                return null
            }
        }

    }
}