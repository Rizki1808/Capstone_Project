package com.example.capstoneproject.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.R
import com.example.capstoneproject.data.response.Article
import com.example.capstoneproject.databinding.ItemNewsBinding
import com.example.capstoneproject.databinding.ItemLoadingBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExploreAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<Article>()
    private var onItemClickCallback: OnItemClickCallback? = null
    private var isLoadingAdded = false

    companion object {
        private const val ITEM = 0
        private const val LOADING = 1
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(data: MutableList<Article>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: List<Article>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        list.add(Article.createLoadingItem())
        notifyItemInserted(list.size - 1)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = list.size - 1
        if (position >= 0) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM) {
            val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ListViewHolder(binding, onItemClickCallback)
        } else {
            val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM) {
            val data = list[position]
            (holder as ListViewHolder).bind(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun getItemCount(): Int = list.size

    class ListViewHolder(
        private val binding: ItemNewsBinding,
        private val onItemClickCallback: OnItemClickCallback?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.apply {
                tvNewsTitle.text = data.title

                // Parse and format the publishedAt date
                val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                val targetFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date: Date? = originalFormat.parse(data.publishedAt ?: "")
                val formattedDate = date?.let { targetFormat.format(it) } ?: data.publishedAt

                tvNewsDate.text = formattedDate
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

    class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClickCallBack(data: Article)
    }
}
