package com.example.herbeauty

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LogSigActivity : AppCompatActivity() {

    private lateinit var btnLoginFirst: Button
    private lateinit var btnSignupFirst: Button
    private lateinit var btnSkip: Button
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_sig)

        // Initialize SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = preferences.edit()

        // Initialize buttons
        btnSignupFirst = findViewById(R.id.btn_signup_first)
        btnLoginFirst = findViewById(R.id.btn_login_first)
        btnSkip = findViewById(R.id.btn_skip)

        // Login button click listener
        btnLoginFirst.setOnClickListener {
            val intent = Intent(this@LogSigActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Signup button click listener
        btnSignupFirst.setOnClickListener {
            val intent = Intent(this@LogSigActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        // Skip button click listener
        btnSkip.setOnClickListener {
            val intent = Intent(this@LogSigActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
