package com.link.fan.app.community

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.data.bean.Comment
import com.link.fan.databinding.LayoutFeedDetailTypeVideoBinding
import com.link.librarymodule.base.adapter.BasePagedListAdapter

class CommunityAdapter : BasePagedListAdapter<Comment, CommunityAdapter.ViewHolder>(

        object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }

        }

) {

    override fun onCreateViewHolder2(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder2(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(binding: LayoutFeedDetailTypeVideoBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}