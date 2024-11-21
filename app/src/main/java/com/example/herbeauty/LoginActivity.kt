package com.example.herbeauty

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConPassword: EditText
    private lateinit var chkShowPassword: CheckBox
    private lateinit var btnLogin: Button
    private lateinit var tvNewUser: TextView
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etConPassword = findViewById(R.id.et_con_password)
        chkShowPassword = findViewById(R.id.chk_showpassword)
        btnLogin = findViewById(R.id.btn_login)
        tvNewUser = findViewById(R.id.tv_new_user)

        // Initialize SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = preferences.edit()

        // Check if the user is already logged in
        if (preferences.getBoolean("isLogin", false)) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Toggle password visibility
        chkShowPassword.setOnCheckedChangeListener { _, isChecked ->
            val method = if (isChecked) HideReturnsTransformationMethod.getInstance() else PasswordTransformationMethod.getInstance()
            etPassword.transformationMethod = method
            etConPassword.transformationMethod = method
        }

        // Handle login button click
        btnLogin.setOnClickListener {
            if (validateInput()) {
                loginUser()
            }
        }

        // Handle new user registration
        tvNewUser.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInput(): Boolean {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val conPassword = etConPassword.text.toString().trim()

        return when {
            username.isEmpty() -> {
                etUsername.error = "Enter Username"
                false
            }
            username.length < 5 -> {
                etUsername.error = "Username must be greater than 5 characters"
                false
            }
            password.isEmpty() -> {
                etPassword.error = "Enter Password"
                false
            }
            password.length < 5 -> {
                etPassword.error = "Password must be greater than 5 characters"
                false
            }
            conPassword.isEmpty() -> {
                etConPassword.error = "Confirm Password"
                false
            }
            password != conPassword -> {
                etConPassword.error = "Confirm Password doesn't match"
                false
            }
            else -> true
        }
    }

    private fun loginUser() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        reference = FirebaseDatabase.getInstance().getReference("User_Profile")
        val checkUserData = reference.orderByChild("username").equalTo(username)

        checkUserData.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val passwordFromDB = userSnapshot.child("password").getValue(String::class.java)
                        if (passwordFromDB == password) {
                            // Clear errors and login
                            etUsername.error = null
                            etPassword.error = null
                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()

                            // Save login state and navigate to MainActivity
                            editor.putBoolean("isLogin", true).apply()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "User Does Not Exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
