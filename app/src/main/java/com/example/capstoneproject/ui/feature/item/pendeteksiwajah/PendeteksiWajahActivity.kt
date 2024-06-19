package com.example.capstoneproject.ui.feature.item.pendeteksiwajah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityPendeteksiWajahBinding

class PendeteksiWajahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPendeteksiWajahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendeteksiWajahBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}