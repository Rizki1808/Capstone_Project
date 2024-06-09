package com.example.capstoneproject.ui.feature.item.minumobat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityMinumObatBinding

class MinumObatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinumObatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMinumObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            val intent = Intent(this, TambahObatActivity::class.java)
            startActivity(intent)
        }
    }
}