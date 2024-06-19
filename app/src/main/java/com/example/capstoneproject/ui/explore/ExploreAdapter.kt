package com.example.capstoneproject.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.data.response.Article
import com.example.capstoneproject.databinding.ItemNewsBinding

class ExploreAdapter: RecyclerView.Adapter<ExploreAdapter.ListViewHolder>() {

    private val list = ArrayList<Article>()

    fun setData(data: MutableList<Article>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreAdapter.ListViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExploreAdapter.ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    class ListViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.apply {
                tvNewsTitle.text = data.title
                tvNewsDate.text = data.publishedAt
                if (data.urlToImage != null) {
                    Glide.with(itemView.context)
                        .load(data.urlToImage)
                        .into(ivPreviewImageInfoNews)
                } else {
                    // Load placeholder image or do nothing
                }
            }
        }
    }
}