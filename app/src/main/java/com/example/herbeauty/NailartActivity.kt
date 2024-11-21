package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NailartActivity : AppCompatActivity() {

    private lateinit var nail1: ImageView
    private lateinit var nail2: ImageView
    private lateinit var nail3: ImageView
    private lateinit var nail4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nailart)

        nail1 = findViewById(R.id.nail1)
        nail2 = findViewById(R.id.nail2)
        nail3 = findViewById(R.id.nail3)
        nail4 = findViewById(R.id.nail4)

        nail1.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java).apply {
                putExtra("floral_nail_art", "Floral Nail Art")
            }
            startActivity(intent)
        }

        nail2.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java).apply {
                putExtra("plastic_wrap_nail_art", "Plastic Wrap Nail Art")
            }
            startActivity(intent)
        }

        nail3.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java).apply {
                putExtra("white_nail_art", "White Nail Art")
            }
            startActivity(intent)
        }

        nail4.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java).apply {
                putExtra("four_nail_art", "Four Nail Art")
            }
            startActivity(intent)
        }
    }
}
