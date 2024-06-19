package com.example.capstoneproject.ui.feature.item.diari.tekanandarah.tambahtekanan

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.example.capstoneproject.ui.tools.DatePickerFragment
import java.util.Calendar

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

        binding.edTgl.setOnClickListener {
            showDatePickerDialog()
        }

        binding.edWaktu.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
            binding.edTgl.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = 0

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val formattedTime = String.format("%02d:%02d:%02d", selectedHour, selectedMinute, second)
            binding.edWaktu.setText(formattedTime)
        }, hour, minute, true)

        timePickerDialog.show()
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
                finish()
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