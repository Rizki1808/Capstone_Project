package com.example.capstoneproject.ui.feature.item.diari.tekanandarah.tambahtekanan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityTambahTekananBinding
import androidx.appcompat.app.AlertDialog
import com.example.capstoneproject.ui.feature.item.diari.tekanandarah.TekananActivity
import androidx.lifecycle.Observer

class TambahTekananActivity : AppCompatActivity() {

    private val viewModel by viewModels<TambahTekananViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityTambahTekananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahTekananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpan.setOnClickListener {
            setupAction()
        }

        viewModel.isLoading.observe(this, Observer { isLoading ->
            showLoading(isLoading)
        })

        viewModel.uploadPressure.observe(this, Observer { result ->
            result.onSuccess {
                showSuccessDialog()
            }.onFailure {
                showErrorDialog(it.message)
            }
        })
    }

    private fun setupAction() {
        val sistolik = binding.edSistolik.text.toString()
        val diastolik = binding.edDiastolik.text.toString()
        val checkDate = binding.edTgl.text.toString()
        val checkTime = binding.edWaktu.text.toString()

        if (sistolik.isEmpty() || diastolik.isEmpty() || checkDate.isEmpty() || checkTime.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Please fill all the fields")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } else {
            viewModel.uploadPressure(sistolik.toInt(), diastolik.toInt(), checkDate, checkTime)
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Success")
            .setMessage("Data Berhasil Disimpan")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, TekananActivity::class.java)
                startActivity(intent)
            }
            .show()
    }

    private fun showErrorDialog(message: String?) {
        val errorMessage = message ?: "An error occurred"
        Log.e("TambahTekananActivity", errorMessage) // Log the error message
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(errorMessage)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}