package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class BodyCareActivity : AppCompatActivity() {

    private lateinit var body1: ImageView
    private lateinit var body2: ImageView
    private lateinit var body3: ImageView
    private lateinit var body4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_care)

        // Initialize views
        body1 = findViewById(R.id.body1)
        body2 = findViewById(R.id.body2)
        body3 = findViewById(R.id.body3)
        body4 = findViewById(R.id.body4)

        // Set onClick listeners using lambda expressions
        body1.setOnClickListener {
            navigateToAppointmentActivity("body_spa", "Body SPA")
        }
        body2.setOnClickListener {
            navigateToAppointmentActivity("body_polishing", "Body Polishing")
        }
        body3.setOnClickListener {
            navigateToAppointmentActivity("body_stream", "Body Stream")
        }
        body4.setOnClickListener {
            navigateToAppointmentActivity("body_wax", "Body Wax")
        }
    }

    // Helper function to navigate to AppointmentActivity
    private fun navigateToAppointmentActivity(key: String, value: String) {
        val intent = Intent(this@BodyCareActivity, AppoinmentActivity::class.java)
        intent.putExtra(key, value)
        startActivity(intent)
    }
}
