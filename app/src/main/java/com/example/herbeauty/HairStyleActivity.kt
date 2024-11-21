package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HairStyleActivity : AppCompatActivity() {

    private lateinit var hair1: ImageView
    private lateinit var hair2: ImageView
    private lateinit var hair3: ImageView
    private lateinit var hair4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_style)

        // Initialize the ImageViews
        hair1 = findViewById(R.id.hair1)
        hair2 = findViewById(R.id.hair2)
        hair3 = findViewById(R.id.hair3)
        hair4 = findViewById(R.id.hair4)

        // Set click listeners for each ImageView
        hair1.setOnClickListener {
            navigateToAppointment("Hair Cuts")
        }
        hair2.setOnClickListener {
            navigateToAppointment("Hair Straightening")
        }
        hair3.setOnClickListener {
            navigateToAppointment("Keratin Treatment")
        }
        hair4.setOnClickListener {
            navigateToAppointment("Hair Straightening")
        }
    }

    // Helper function to navigate to the Appointment Activity
    private fun navigateToAppointment(service: String) {
        val intent = Intent(this@HairStyleActivity, AppoinmentActivity::class.java)
        intent.putExtra(service.replace(" ", "_").lowercase(), service)
        startActivity(intent)
    }
}
