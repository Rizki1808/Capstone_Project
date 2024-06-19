package com.example.capstoneproject.ui.feature.item.pendeteksikulit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityHasilKulitBinding
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitActivity

class HasilKulitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilKulitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilKulitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive data from Intent
        val result = intent.getStringExtra("RESULT")
        val imageUriString = intent.getStringExtra("IMAGE_URI")
        val imageUri = Uri.parse(imageUriString)

        // Update UI
        binding.tvHasil.text = result
        binding.ivPreviewImageHasilKulit.setImageURI(imageUri)

        binding.btnFind.setOnClickListener{
            val intent = Intent(this, InfoPenyakitActivity::class.java)
            startActivity(intent)
        }
    }
}