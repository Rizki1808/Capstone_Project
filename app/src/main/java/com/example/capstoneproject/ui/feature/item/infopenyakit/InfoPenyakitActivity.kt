package com.example.capstoneproject.ui.feature.item.infopenyakit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityInfoPenyakitBinding

class InfoPenyakitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoPenyakitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}