package com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksikulit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.databinding.ActivityHasilBinding
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitActivity
import com.example.capstoneproject.ui.feature.item.pendeteksi.history.HistoryKulitActivity

class HasilKulitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive data from Intent
        val result = intent.getStringExtra("RESULT")
        val imageUriString = intent.getStringExtra("IMAGE_URI")
        val imageUri = Uri.parse(imageUriString)

        // Update UI
        binding.tvHasil.text = result
        binding.ivPreviewImageHasilKulit.setImageURI(imageUri)

        binding.ivHistoryKulit.setOnClickListener {
            val intent = Intent(this, HistoryKulitActivity::class.java)
            startActivity(intent)
        }

        binding.btnFind.setOnClickListener{
            val intent = Intent(this, InfoPenyakitActivity::class.java)
            startActivity(intent)
        }
    }
}