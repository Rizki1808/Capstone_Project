package com.example.capstoneproject.ui.profile.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityDetailNgdBinding

class DetailNGD : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNgdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNgdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set onClickListeners on the CardViews
        binding.cvKgd.setOnClickListener { dialNumber("119") }
        binding.cvSik.setOnClickListener { dialNumber("0214250767") }
        binding.cvPmi.setOnClickListener { dialNumber("021 7992325") }
        binding.cvPbk.setOnClickListener { dialNumber("1500567") }
    }

    private fun dialNumber(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(dialIntent)
    }
}
