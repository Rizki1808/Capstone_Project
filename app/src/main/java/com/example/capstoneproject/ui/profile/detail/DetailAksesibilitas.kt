package com.example.capstoneproject.ui.profile.detail


import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.capstoneproject.databinding.ActivityDetailAksesibilitasBinding

class DetailAksesibilitas : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityDetailAksesibilitasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityDetailAksesibilitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("notification_prefs", MODE_PRIVATE)

        // Apply window insets to adjust padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup switch listener
        binding.btnSwitch.isChecked = sharedPreferences.getBoolean("notifications_enabled", false) // Restore switch state
        binding.btnSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save switch state to SharedPreferences
            sharedPreferences.edit().putBoolean("notifications_enabled", isChecked).apply()

            if (isChecked) {
                enableNotifications()
            } else {
                disableNotifications()
            }
        }
    }

    // Enable notifications
    private fun enableNotifications() {
        // Implement your logic for enabling notifications here
        // This could involve starting a service or enabling a specific notification channel
        // For example:
        val notificationManager = NotificationManagerCompat.from(this)
        // Add your notification enabling logic here
    }

    // Disable notifications
    private fun disableNotifications() {
        // Implement your logic for disabling notifications here
        // This could involve stopping a service or disabling a specific notification channel
        // For example:
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.cancelAll() // Cancels all notifications
    }
}
