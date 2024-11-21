package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SkinCareActivity : AppCompatActivity() {

    private lateinit var skin1: ImageView
    private lateinit var skin2: ImageView
    private lateinit var skin3: ImageView
    private lateinit var skin4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skin_care)

        // Initialize ImageViews
        skin1 = findViewById(R.id.skin1)
        skin2 = findViewById(R.id.skin2)
        skin3 = findViewById(R.id.skin3)
        skin4 = findViewById(R.id.skin4)

        // Set click listeners for each ImageView
        skin1.setOnClickListener { startAppointment("facial", "Facial") }
        skin2.setOnClickListener { startAppointment("face_cleanup", "Face CleanUp") }
        skin3.setOnClickListener { startAppointment("face_bleach", "Face Bleach") }
        skin4.setOnClickListener { startAppointment("face_moisturizing", "Face Moisturizing") }
    }

    // Helper function to start the appointment activity with the selected treatment
    private fun startAppointment(key: String, value: String) {
        val intent = Intent(this@SkinCareActivity, AppoinmentActivity::class.java)
        intent.putExtra(key, value)
        startActivity(intent)
    }
}
