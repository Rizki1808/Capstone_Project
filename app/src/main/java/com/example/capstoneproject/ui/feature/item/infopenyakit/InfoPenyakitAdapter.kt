package com.example.capstoneproject.ui.feature.item.infopenyakit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.data.response.ListStoryItem
import com.example.capstoneproject.databinding.ItemPenyakitBinding

class InfoPenyakitAdapter :
    PagingDataAdapter<ListStoryItem, InfoPenyakitAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPenyakitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
        }
    }

    inner class ListViewHolder(private val binding: ItemPenyakitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListStoryItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClickCallBack(data)
            }
            binding.apply {
                tvTitle.text = data.name
                tvDescription.text = data.description
                Glide.with(itemView.context)
                    .load(data.photoUrl)
                    .into(ivPreviewImageInfo)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClickCallBack(data: ListStoryItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}