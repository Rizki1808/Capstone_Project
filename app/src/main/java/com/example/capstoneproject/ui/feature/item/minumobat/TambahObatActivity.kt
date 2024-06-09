package com.example.capstoneproject.ui.feature.item.minumobat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityTambahObatBinding

class TambahObatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahObatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahObatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}