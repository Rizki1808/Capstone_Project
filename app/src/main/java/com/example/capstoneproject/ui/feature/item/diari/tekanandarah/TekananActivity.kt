package com.example.capstoneproject.ui.feature.item.diari.tekanandarah

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityTekananBinding
import androidx.lifecycle.Observer

class TekananActivity : AppCompatActivity() {

    private val viewModel by viewModels<TekananViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityTekananBinding
    private lateinit var adapter: TekananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTekananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TekananAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvPressure.layoutManager = LinearLayoutManager(this@TekananActivity)
            rvPressure.setHasFixedSize(true)
            rvPressure.adapter = adapter
        }

        viewModel.pressure.observe(this, Observer { result ->
            result.onSuccess { pressureResponse ->
                ArrayList(pressureResponse.data).let { adapter.setData(it) }
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

        viewModel.getPressure()

        binding?.fab?.setOnClickListener { view ->
            val intent = Intent(this, TambahTekananActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}