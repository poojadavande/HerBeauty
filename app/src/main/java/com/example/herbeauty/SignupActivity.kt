package com.example.herbeauty

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.herbeauty.FirebaseExtraClasses.RegisterUserHelperClass
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var btnSignup: Button
    private lateinit var etSigName: EditText
    private lateinit var etSigUsername: EditText
    private lateinit var etSigPass: EditText
    private lateinit var etSigConformPass: EditText
    private lateinit var etSigMobileNo: EditText
    private lateinit var etSigEmailId: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        title = "Signup Activity"

        // Initialize views
        btnSignup = findViewById(R.id.btn_signup)
        etSigName = findViewById(R.id.et_sig_name)
        etSigUsername = findViewById(R.id.et_sig_username)
        etSigPass = findViewById(R.id.et_sig_pass)
        etSigConformPass = findViewById(R.id.et_sig_con_pass)
        etSigMobileNo = findViewById(R.id.et_sig_mobile_no)
        etSigEmailId = findViewById(R.id.et_sig_email)

        // Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("User_Profile")

        btnSignup.setOnClickListener {
            val fullName = etSigName.text.toString().trim()
            val userName = etSigUsername.text.toString().trim()
            val password = etSigPass.text.toString().trim()
            val conformPassword = etSigConformPass.text.toString().trim()
            val mobileNo = etSigMobileNo.text.toString().trim()
            val emailId = etSigEmailId.text.toString().trim()

            // Validate inputs
            when {
                fullName.isEmpty() -> etSigName.error = "Please Enter Full Name"
                fullName.length < 6 -> etSigName.error = "Name must contain at least 6 characters"
                userName.isEmpty() -> etSigUsername.error = "Please Enter Username"
                userName.length < 5 -> etSigUsername.error = "Username must contain at least 5 characters"
                password.isEmpty() -> etSigPass.error = "Please Enter Password"
                password.length < 5 -> etSigPass.error = "Password must contain at least 5 characters"
                conformPassword.isEmpty() -> etSigConformPass.error = "Please Confirm Password"
                conformPassword != password -> etSigConformPass.error = "Passwords don't match"
                mobileNo.isEmpty() -> etSigMobileNo.error = "Please Enter Mobile No."
                mobileNo.length != 10 -> etSigMobileNo.error = "Mobile No must contain 10 digits"
                emailId.isEmpty() -> etSigEmailId.error = "Please Enter Email Id"
                !emailId.contains("@") || !emailId.contains(".") -> etSigEmailId.error = "Invalid Email Id"
                else -> registerUser(fullName, userName, password, mobileNo, emailId)
            }
        }
    }

    private fun registerUser(fullName: String, userName: String, password: String, mobileNo: String, emailId: String) {
        val registerUserHelperClass = RegisterUserHelperClass(fullName, userName, password, mobileNo, emailId)

        databaseReference.child(userName).setValue(registerUserHelperClass)
            .addOnSuccessListener {
                Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
                Log.d("SignupActivity", "User registered successfully: $userName")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Unable to Register User", Toast.LENGTH_SHORT).show()
                Log.e("SignupActivity", "Error: ${exception.message}")
            }
    }
}
