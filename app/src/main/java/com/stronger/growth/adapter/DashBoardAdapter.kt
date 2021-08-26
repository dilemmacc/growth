package com.stronger.growth.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stronger.growth.databinding.ListItemDashboardBinding

/**
 * @author rachel.zhao on 2021/8/23.
 */
class DashBoardAdapter : ListAdapter<String, RecyclerView.ViewHolder>(differCallback) {
    companion object {
        val differCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return false
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ListItemDashboardBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ItemViewHolder).bind(data)
    }

    class ItemViewHolder(
        private val binding: ListItemDashboardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {

            }
        }

        fun bind(item: String) {
            binding.apply {
                dataItem = item
                executePendingBindings()
            }
        }
    }
}