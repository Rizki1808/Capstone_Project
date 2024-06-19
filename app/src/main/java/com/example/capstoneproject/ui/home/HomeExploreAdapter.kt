package com.example.capstoneproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.data.response.Article
import com.example.capstoneproject.databinding.ItemHomeNewsBinding

class HomeExploreAdapter : RecyclerView.Adapter<HomeExploreAdapter.ListViewHolder>() {

    private val list = ArrayList<Article>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(data: MutableList<Article>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHomeNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding, onItemClickCallback)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    class ListViewHolder(
        private val binding: ItemHomeNewsBinding,
        private val onItemClickCallback: OnItemClickCallback?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.apply {
                tvNewsTitle.text = data.title
                tvNewsDate.text = data.publishedAt
                if (data.urlToImage != null) {
                    Glide.with(itemView.context)
                        .load(data.urlToImage)
                        .into(ivPreviewImageInfoNews)
                } else {
                    // Load placeholder image
                }
                root.setOnClickListener {
                    onItemClickCallback?.onItemClickCallBack(data)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClickCallBack(data: Article)
    }
}