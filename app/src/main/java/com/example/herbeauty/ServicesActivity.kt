package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ServicesActivity : AppCompatActivity() {

    private lateinit var crdHairstyle: CardView
    private lateinit var crdSkincare: CardView
    private lateinit var crdMakeupart: CardView
    private lateinit var crdNailart: CardView
    private lateinit var crdBodycare: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)
        title = "Services"

        crdHairstyle = findViewById(R.id.crd_hairstyle)
        crdSkincare = findViewById(R.id.crd_skincare)
        crdMakeupart = findViewById(R.id.crd_makeupart)
        crdNailart = findViewById(R.id.crd_nailart)
        crdBodycare = findViewById(R.id.crd_bodycare)

        crdHairstyle.setOnClickListener {
            val intent = Intent(this, HairStyleActivity::class.java)
            startActivity(intent)
        }

        crdSkincare.setOnClickListener {
            val intent = Intent(this, SkinCareActivity::class.java)
            startActivity(intent)
        }

        crdMakeupart.setOnClickListener {
            val intent = Intent(this, MakeupartActivity::class.java)
            startActivity(intent)
        }

        crdNailart.setOnClickListener {
            val intent = Intent(this, NailartActivity::class.java)
            startActivity(intent)
        }

        crdBodycare.setOnClickListener {
            val intent = Intent(this, BodyCareActivity::class.java)
            startActivity(intent)
        }
    }
}
