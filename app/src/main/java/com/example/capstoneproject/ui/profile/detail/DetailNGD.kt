package com.example.capstoneproject.ui.profile.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.capstoneproject.R

class DetailNGD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ngd)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupCallButtons()
    }

    private fun setupCallButtons() {
        findViewById<TextView>(R.id.txt_kgd).setOnClickListener {
            dialPhoneNumber("119")
        }
        findViewById<TextView>(R.id.txt_pill).setOnClickListener {
            dialPhoneNumber("0814250767")
        }
        findViewById<TextView>(R.id.txt_pmi).setOnClickListener {
            dialPhoneNumber("08217992325")
        }
        findViewById<TextView>(R.id.txt_cs).setOnClickListener {
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
