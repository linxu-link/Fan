package com.link.fan.app.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.link.fan.data.bean.MenuDetail
import com.link.fan.databinding.ListItemHomeBinding
import com.link.fan.databinding.ListItemHomeHeadBinding

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-06-11:10
 * email:wujia0916@thundersoft.com
 * description:
 */
class RecommendAdapter : ListAdapter<MenuDetail, RecommendAdapter.RecommendHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendHolder {
        return RecommendHolder(ListItemHomeHeadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecommendHolder, position: Int) {
        val menuResult = getItem(position)
        holder.bind(menuResult)
    }


    class RecommendHolder constructor(private val binding: ListItemHomeHeadBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.menuDetail?.let { menuDetail ->
                    navigateToMenu(menuDetail, it)
                }
            }
        }

        private fun navigateToMenu(menuDetail: MenuDetail, it: View) {
            //TODO
            val direction: NavDirections? = null
//            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(plant.plantId)
            it.findNavController().navigate(direction!!)
        }

        fun bind(item: MenuDetail) {
            binding.apply {
                menuDetail = item
                executePendingBindings()
            }
        }

    }


}

private class HomeDiffCallback : DiffUtil.ItemCallback<MenuDetail>() {
    override fun areItemsTheSame(oldItem: MenuDetail, newItem: MenuDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MenuDetail, newItem: MenuDetail): Boolean {
        return oldItem == newItem
    }

}