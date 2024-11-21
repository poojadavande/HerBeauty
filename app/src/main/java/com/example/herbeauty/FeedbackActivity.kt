package com.example.herbeauty

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.herbeauty.FirebaseExtraClasses.StoreFeedbackHelperClass
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FeedbackActivity : AppCompatActivity() {

    private lateinit var btnFeedback: Button
    private lateinit var etFeedback: EditText
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference = database.getReference("User_Feedback")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        title = "Feedback"

        // Initialize views
        btnFeedback = findViewById(R.id.btn_feedback)
        etFeedback = findViewById(R.id.et_feedback)

        // Set click listener on feedback button
        btnFeedback.setOnClickListener {
            storeFeedback()
            Toast.makeText(this, "Feedback Sent Successfully!", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Thanks For Your Valuable Feedback😊", Toast.LENGTH_LONG).show()
            etFeedback.text.clear()
        }
    }

    private fun storeFeedback() {
        val feedbackText = etFeedback.text.toString()
        if (feedbackText.isNotEmpty()) {
            val storeFeedbackHelperClass = StoreFeedbackHelperClass(feedbackText)
            reference.child(feedbackText).setValue(storeFeedbackHelperClass)
        } else {
            Toast.makeText(this, "Please provide feedback before submitting.", Toast.LENGTH_SHORT).show()
        }
    }
}
