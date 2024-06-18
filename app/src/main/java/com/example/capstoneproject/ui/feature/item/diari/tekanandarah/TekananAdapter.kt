package com.example.capstoneproject.ui.feature.item.diari.tekanandarah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.response.DataBlood
import com.example.capstoneproject.databinding.ItemTekananBinding

class TekananAdapter: RecyclerView.Adapter<TekananAdapter.ListViewHolder>() {

    private val list = ArrayList<DataBlood>()

    fun setData(data: MutableList<DataBlood>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTekananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemTekananBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: DataBlood) {
                binding.apply {
                    tvSistolik.text = data.sistolik.toString()
                    tvDiastolik.text = data.distolik.toString()
                    tvDate.text = data.checkDate.toString()
                    tvTime.text = data.checkTime.toString()
                }
            }
        }
}