package com.example.capstoneproject.ui.feature.item.diari.guladarah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.response.DataSugar
import com.example.capstoneproject.databinding.ItemGulaBinding

class GulaAdapter: RecyclerView.Adapter<GulaAdapter.ListViewHolder>() {

    private val list = ArrayList<DataSugar>()

    fun setData(data: MutableList<DataSugar>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GulaAdapter.ListViewHolder {
        val binding = ItemGulaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GulaAdapter.ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    class ListViewHolder(private val binding: ItemGulaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataSugar) {
            binding.apply {
                tvGula.text = data.bloodSugar.toString()
                tvDate.text = data.checkDate.toString()
                tvTime.text = data.checkTime.toString()
            }
        }
    }
}