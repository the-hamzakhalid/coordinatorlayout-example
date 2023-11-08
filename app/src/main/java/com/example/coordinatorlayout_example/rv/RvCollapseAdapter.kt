package com.example.coordinatorlayout_example.rv

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coordinatorlayout_example.databinding.ItemRvBinding


/**
 * Created by Hamza Khalid
 * Sr. Software Engineer
 * Created on 18 Oct,2023 18:11
 * Copyright (c) All rights reserved.
 * @see "<a href="https://www.linkedin.com/in/the-hamzakhalid/">Linkedin Profile</a>"
 */

class RvCollapseAdapter(
    val action: (pos: Int, name: String) -> Unit
) : ListAdapter<String, RvCollapseAdapter.ItemViewHolder>(DiffCallback()) {
    private val TAG = RvCollapseAdapter::class.java.simpleName

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ItemViewHolder {
        val binding =
            ItemRvBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, pos: Int) {
        val item = currentList[pos]
        viewHolder.bind(item)

    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    inner class ItemViewHolder(private val itemBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(item: String) {
            Log.d(TAG, "bind-> Device : $item ")
            itemBinding.run {

                itemView.setOnClickListener {

                }


            }
        }


    }


    private class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}