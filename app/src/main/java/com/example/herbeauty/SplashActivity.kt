package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var logoImg: ImageView
    private lateinit var herBeauty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initialize views
        logoImg = findViewById(R.id.logo_img)
        herBeauty = findViewById(R.id.her_beauty)

        // Load fade-in animation
        val fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fadein)

        // Set animation to both views
        logoImg.startAnimation(fadeInAnim)
        herBeauty.startAnimation(fadeInAnim)

        // Use Handler to delay the transition
        Handler().postDelayed({
            startActivity(Intent(this, LogSigActivity::class.java))
            finish()
        }, 3000)
    }
}
