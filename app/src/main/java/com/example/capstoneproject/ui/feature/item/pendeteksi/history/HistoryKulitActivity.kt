package com.example.capstoneproject.ui.feature.item.pendeteksi.history

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityHistoryBinding

class HistoryKulitActivity : AppCompatActivity() {

    private val viewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HistoryAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvHistory.layoutManager = LinearLayoutManager(this@HistoryKulitActivity)
            rvHistory.setHasFixedSize(true)
            rvHistory.adapter = adapter
        }

        viewModel.getSkin.observe(this, Observer { result ->
            result.onSuccess { skinResponse ->
                ArrayList(skinResponse.data).let { adapter.setData(it) }
                showLoading(false)
            }
            result.onFailure { exception ->
                showLoading(false)
                // Handle error
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            showLoading(isLoading)
        })

        viewModel.getSkinDetection()
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}