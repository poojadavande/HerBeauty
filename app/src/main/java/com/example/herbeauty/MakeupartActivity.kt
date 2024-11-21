package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MakeupartActivity : AppCompatActivity() {

    private lateinit var makeup1: ImageView
    private lateinit var makeup2: ImageView
    private lateinit var makeup3: ImageView
    private lateinit var makeup4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makeupart)

        makeup1 = findViewById(R.id.makeup1)
        makeup2 = findViewById(R.id.makeup2)
        makeup3 = findViewById(R.id.makeup3)
        makeup4 = findViewById(R.id.makeup4)

        makeup1.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java)
            intent.putExtra("bridal_makeup", "Bridal Makeup")
            startActivity(intent)
        }

        makeup2.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java)
            intent.putExtra("3d_makeup", "3D Makeup")
            startActivity(intent)
        }

        makeup3.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java)
            intent.putExtra("hd_makeup", "HD Makeup")
            startActivity(intent)
        }

        makeup4.setOnClickListener {
            val intent = Intent(this, AppoinmentActivity::class.java)
            intent.putExtra("waterproof_makeup", "Waterproof Makeup")
            startActivity(intent)
        }
    }
}
