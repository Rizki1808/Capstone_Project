package com.example.capstoneproject.ui.feature.item.diari.guladarah

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityGulaBinding
import androidx.lifecycle.Observer
import com.example.capstoneproject.ui.feature.item.diari.guladarah.tambahgula.TambahGulaActivity

class GulaActivity : AppCompatActivity() {

    private val viewModel by viewModels<GulaViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityGulaBinding
    private lateinit var adapter: GulaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGulaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GulaAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvSugar.layoutManager = LinearLayoutManager(this@GulaActivity)
            rvSugar.setHasFixedSize(true)
            rvSugar.adapter = adapter

            swipeRefreshLayout.setOnRefreshListener {
                refreshData()
            }
        }

        viewModel.sugar.observe(this, Observer { result ->
            result.onSuccess { sugarResponse ->
                ArrayList(sugarResponse.data).let { adapter.setData(it) }
                showLoading(false)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            result.onFailure { exception ->
                showLoading(false)
                binding.swipeRefreshLayout.isRefreshing = false
                // Handle error
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            showLoading(isLoading)
        })

        viewModel.getSugar()

        binding?.fab?.setOnClickListener { view ->
            val intent = Intent(this, TambahGulaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshData() {
        viewModel.getSugar()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}