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

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-06-11:10
 * email:wujia0916@thundersoft.com
 * description:
 */
class HomeAdapter : ListAdapter<MenuDetail, HomeAdapter.HomeHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(ListItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val menuResult = getItem(position)
        holder.bind(menuResult)
    }

    class HomeHolder(private val binding: ListItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.menu?.let { menu ->
                    navigateToMenu(menu, it)
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
                menu = item
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