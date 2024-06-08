package com.example.capstoneproject.ui.feature.item

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityPendeteksiKulitBinding

class PendeteksiKulitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPendeteksiKulitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendeteksiKulitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}