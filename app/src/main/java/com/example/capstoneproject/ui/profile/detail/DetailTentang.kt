package com.example.capstoneproject.ui.profile.detail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.capstoneproject.R

class DetailTentang : AppCompatActivity() {

    private lateinit var dcSnk: TextView
    private lateinit var dcKontak: TextView
    private lateinit var dcVersiapp: TextView

    private lateinit var expandButtonSnk: ImageButton
    private lateinit var expandButtonKontak: ImageButton
    private lateinit var expandButtonVersiapp: ImageButton

    private var isSnkExpanded = false
    private var isKontakExpanded = false
    private var isVersiappExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tentang)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Apply window insets to handle display cutouts
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.back_up)
        button.setOnClickListener {
            finish()
        }

        // Initialize views
        dcSnk = findViewById(R.id.dc_snk)
        dcKontak = findViewById(R.id.dc_kontak)
        dcVersiapp = findViewById(R.id.dc_versiapp)

        expandButtonSnk = findViewById(R.id.btn_snk)
        expandButtonKontak = findViewById(R.id.btn_kontak)
        expandButtonVersiapp = findViewById(R.id.btn_Vapp)

        // Set click listeners for expand buttons
        expandButtonSnk.setOnClickListener {
            if (isSnkExpanded) {
                dcSnk.visibility = TextView.GONE
                expandButtonSnk.setImageResource(R.drawable.ic_arr_down) // Change to down arrow icon
            } else {
                dcSnk.visibility = TextView.VISIBLE
                expandButtonSnk.setImageResource(R.drawable.ic_arr_down) // Change to up arrow icon
            }
            isSnkExpanded = !isSnkExpanded
        }

        expandButtonKontak.setOnClickListener {
            if (isKontakExpanded) {
                dcKontak.visibility = TextView.GONE
                expandButtonKontak.setImageResource(R.drawable.ic_arr_down) // Change to down arrow icon
            } else {
                dcKontak.visibility = TextView.VISIBLE
                expandButtonKontak.setImageResource(R.drawable.ic_arr_down) // Change to up arrow icon
            }
            isKontakExpanded = !isKontakExpanded
        }

        expandButtonVersiapp.setOnClickListener {
            if (isVersiappExpanded) {
                dcVersiapp.visibility = TextView.GONE
                expandButtonVersiapp.setImageResource(R.drawable.ic_arr_down) // Change to down arrow icon
            } else {
                dcVersiapp.visibility = TextView.VISIBLE
                expandButtonVersiapp.setImageResource(R.drawable.ic_arr_down) // Change to up arrow icon
            }
            isVersiappExpanded = !isVersiappExpanded
        }
    }

    // Method to enable edge-to-edge display
    private fun enableEdgeToEdge() {
        // Implement edge-to-edge display functionality if needed
        // Example:
        // window.insetsController?.apply {
        //    hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        //    systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
        // }
    }
}
