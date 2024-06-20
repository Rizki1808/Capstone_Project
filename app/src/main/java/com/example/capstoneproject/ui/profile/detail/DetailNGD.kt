package com.example.capstoneproject.ui.profile.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.capstoneproject.databinding.ActivityDetailNgdBinding

class DetailNGD : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNgdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNgdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupCallButtons()
    }

    override fun onResume() {
        super.onResume()
        setupCallButtons()
    }

    private fun setupCallButtons() {
        // KGD section
        binding.txtKgd.setOnClickListener {
            dialPhoneNumber("119")
        }
        binding.imgKgd.setOnClickListener {
            dialPhoneNumber("119")
        }

        // Pill section
        binding.txtPill.setOnClickListener {
            dialPhoneNumber("0814250767")
        }
        binding.imgPill.setOnClickListener {
            dialPhoneNumber("0814250767")
        }

        // PMI section
        binding.txtPmi.setOnClickListener {
            dialPhoneNumber("08217992325")
        }
        binding.imgPmi.setOnClickListener {
            dialPhoneNumber("08217992325")
        }

        // CS section
        binding.txtCs.setOnClickListener {
            dialPhoneNumber("1500567")
        }
        binding.imgCs.setOnClickListener {
            dialPhoneNumber("1500567")
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
