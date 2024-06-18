package com.example.capstoneproject.ui.feature.item.infopenyakit.detailpenyakit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityDetailPenyakitBinding

class DetailPenyakitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPenyakitBinding
    private lateinit var viewModel: DetailPenyakitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(DetailPenyakitViewModel::class.java)

        val diseaseId = intent.getStringExtra(EXTRA_DISEASE)
        if (diseaseId != null) {
            showLoading(true)
            viewModel.getDiseaseDetail(diseaseId)
        }

        viewModel.diseases.observe(this, Observer { result ->
            result.onSuccess { diseases ->
                println("Fetched disease detail: $diseases")
                binding.apply {
                    tvNamaPenyakit.text = diseases.name
                    description.text = diseases.description
                    prevention.text = diseases.prevention
                    treatment.text = diseases.treatment
                    lastUpdate.text = diseases.updatedAt
                    Glide.with(this@DetailPenyakitActivity)
                        .load(diseases.image)
                        .into(ivPreviewImagePenyakit)
                }
                showLoading(false)
            }
            result.onFailure {
                showLoading(false)
                // Handle error
            }
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
            showLoading(isLoading)
        })
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_DISEASE = "extra_disease"
    }
}
