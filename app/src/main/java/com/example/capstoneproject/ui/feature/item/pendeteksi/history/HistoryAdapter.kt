package com.example.capstoneproject.ui.feature.item.pendeteksi.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.data.response.DataHistory
import com.example.capstoneproject.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {

    private val list = ArrayList<DataHistory>()
    fun setData(data: MutableList<DataHistory>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataHistory) {
            binding.apply {
                tvName.text = data.result

                // Parse and format the createdAt date
                val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val targetFormat = SimpleDateFormat("HH:mm:ss, yyyy-MM-dd", Locale.getDefault())
                val date: Date? = originalFormat.parse(data.createdAt ?: "")
                val formattedDate = date?.let { targetFormat.format(it) } ?: data.createdAt

                tvDate.text = formattedDate
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivPreviewImageInfoHistory)
            }
        }
        }
}