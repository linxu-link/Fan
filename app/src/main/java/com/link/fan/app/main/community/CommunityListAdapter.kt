package com.link.fan.app.main.community

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.app.community.CommunityDetailActivity
import com.link.fan.databinding.ItemListCommunityBinding
import com.link.librarymodule.utils.Utils

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-06-11:10
 * email:wujia0916@thundersoft.com
 * description:
 */
class CommunityListAdapter : ListAdapter<Community, CommunityListAdapter.CommunityListHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityListHolder {
        return CommunityListHolder(ItemListCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommunityListHolder, position: Int) {
        val community = getItem(position)
        holder.bind(community)
    }
    
    class CommunityListHolder constructor(private val binding: ItemListCommunityBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.communityEnity?.objectId?.let { objectId ->
                    navigateToMenu(objectId, it)
                }
            }
        }

        private fun navigateToMenu(objectId: String, view: View) {
            val intent = Intent(Utils.getContext(), CommunityDetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            Utils.getContext().startActivity(intent)
        }

        fun bind(item: Community) {
            with(binding) {
                communityEnity = item
                executePendingBindings()
            }
        }

    }


}

private class HomeDiffCallback : DiffUtil.ItemCallback<Community>() {

    override fun areItemsTheSame(oldItem: Community, newItem: Community): Boolean {
        return oldItem.objectId == newItem.objectId
    }

    override fun areContentsTheSame(oldItem: Community, newItem: Community): Boolean {
        return oldItem.equals(newItem)
    }

}