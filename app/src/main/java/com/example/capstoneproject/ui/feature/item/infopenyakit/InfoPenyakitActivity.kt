package com.example.capstoneproject.ui.feature.item.infopenyakit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.data.response.DataItem
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityInfoPenyakitBinding
import com.example.capstoneproject.ui.feature.item.infopenyakit.detailpenyakit.DetailPenyakitActivity

class InfoPenyakitActivity : AppCompatActivity() {

    private val viewModel by viewModels<InfoPenyakitViewModel>() {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityInfoPenyakitBinding
    private lateinit var adapter: InfoPenyakitAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = InfoPenyakitAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : InfoPenyakitAdapter.OnItemClickCallback {
            override fun onItemClickCallBack(data: DataItem) {
                val intent = Intent(this@InfoPenyakitActivity, DetailPenyakitActivity::class.java)
                intent.putExtra(DetailPenyakitActivity.EXTRA_DISEASE, data.id)
                startActivity(intent)
            }
        })

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@InfoPenyakitActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }

        viewModel.diseases.observe(this, Observer { result ->
            result.onSuccess { diseasesResponse ->
                ArrayList(diseasesResponse.data).let { adapter.setData(it) }
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

        viewModel.getDiseases()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}