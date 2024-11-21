package com.example.herbeauty

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var crdServices: CardView
    private lateinit var crdGallery: CardView
    private lateinit var crdAppointment: CardView
    private lateinit var crdFeedback: CardView
    private lateinit var vFlipper: ViewFlipper
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = preferences.edit()

        title = "Her Beauty"

        crdServices = findViewById(R.id.crd_services)
        crdGallery = findViewById(R.id.crd_gallery)
        crdAppointment = findViewById(R.id.crd_appoiment)
        crdFeedback = findViewById(R.id.crd_feedback)
        vFlipper = findViewById(R.id.v_flipper)

        // For Image Sliding On Main Screen
        val images = intArrayOf(R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5)

        // Loop for sliding images continuously
        for (image in images) {
            flipperImage(image)
        }

        // Setting click listeners
        crdServices.setOnClickListener {
            startActivity(Intent(this, ServicesActivity::class.java))
        }
        crdGallery.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
        crdAppointment.setOnClickListener {
            startActivity(Intent(this, AppoinmentActivity::class.java))
        }
        crdFeedback.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutus -> {
                startActivity(Intent(this, AboutusActivity::class.java))
            }
            R.id.home_menu_logout -> {
                AlertDialog.Builder(this)
                    .setTitle("LogOut")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Cancel") { dialog, _ -> dialog.cancel() }
                    .setNegativeButton("LogOut") { _, _ ->
                        editor.putBoolean("isLogin", false).apply()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    .create()
                    .show()
            }
        }
        return true
    }

    private fun flipperImage(image: Int) {
        val imageView = ImageView(this)
        imageView.setBackgroundResource(image)
        vFlipper.addView(imageView)

        vFlipper.flipInterval = 2000
        vFlipper.isAutoStart = true

        // Animation
        vFlipper.setInAnimation(this, android.R.anim.slide_in_left)
        vFlipper.setOutAnimation(this, android.R.anim.slide_out_right)
    }
}
