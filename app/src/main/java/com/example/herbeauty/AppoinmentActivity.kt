package com.example.herbeauty

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.herbeauty.FirebaseExtraClasses.UserAppointmentHelperClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class AppoinmentActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var mobile: EditText
    private lateinit var email: EditText
    private lateinit var service: EditText
    private lateinit var date: AutoCompleteTextView
    private lateinit var sendWhatsappBtn: Button

    private var hairCutType: String? = null
    private var hairStraightening: String? = null
    private var keratinTreatment: String? = null
    private var facial: String? = null
    private var faceCleanup: String? = null
    private var faceBleach: String? = null
    private var faceMoisturizing: String? = null
    private var bridalMakeup: String? = null
    private var threeDMakeup: String? = null
    private var hdMakeup: String? = null
    private var waterproofMakeup: String? = null
    private var floralNailArt: String? = null
    private var plasticWrapNailArt: String? = null
    private var whiteNailArt: String? = null
    private var fourNailArt: String? = null
    private var bodySPA: String? = null
    private var bodyPolishing: String? = null
    private var bodyStream: String? = null
    private var bodyWax: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appoinment)
        title = "Appoinment"

        name = findViewById(R.id.name_id)
        mobile = findViewById(R.id.mobile_id)
        email = findViewById(R.id.email_id)
        service = findViewById(R.id.service_id)
        date = findViewById(R.id.date_id)
        sendWhatsappBtn = findViewById(R.id.send_whatsapp_btn)

        date.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePicker()
            }
        }

        val intent = intent
        intent?.let {
            when {
                it.hasExtra("hair_cuts") -> {
                    hairCutType = it.getStringExtra("hair_cuts")
                    service.setText(hairCutType)
                }
                it.hasExtra("hair_straightening") -> {
                    hairStraightening = it.getStringExtra("hair_straightening")
                    service.setText(hairStraightening)
                }
                it.hasExtra("hair_keratin") -> {
                    keratinTreatment = it.getStringExtra("hair_keratin")
                    service.setText(keratinTreatment)
                }
                it.hasExtra("facial") -> {
                    facial = it.getStringExtra("facial")
                    service.setText(facial)
                }
                it.hasExtra("face_cleanup") -> {
                    faceCleanup = it.getStringExtra("face_cleanup")
                    service.setText(faceCleanup)
                }
                it.hasExtra("face_bleach") -> {
                    faceBleach = it.getStringExtra("face_bleach")
                    service.setText(faceBleach)
                }
                it.hasExtra("face_moisturizing") -> {
                    faceMoisturizing = it.getStringExtra("face_moisturizing")
                    service.setText(faceMoisturizing)
                }
                it.hasExtra("bridal_makeup") -> {
                    bridalMakeup = it.getStringExtra("bridal_makeup")
                    service.setText(bridalMakeup)
                }
                it.hasExtra("3d_makeup") -> {
                    threeDMakeup = it.getStringExtra("3d_makeup")
                    service.setText(threeDMakeup)
                }
                it.hasExtra("hd_makeup") -> {
                    hdMakeup = it.getStringExtra("hd_makeup")
                    service.setText(hdMakeup)
                }
                it.hasExtra("waterproof_makeup") -> {
                    waterproofMakeup = it.getStringExtra("waterproof_makeup")
                    service.setText(waterproofMakeup)
                }
                it.hasExtra("floral_nail_art") -> {
                    floralNailArt = it.getStringExtra("floral_nail_art")
                    service.setText(floralNailArt)
                }
                it.hasExtra("plastic_wrap_nail_art") -> {
                    plasticWrapNailArt = it.getStringExtra("plastic_wrap_nail_art")
                    service.setText(plasticWrapNailArt)
                }
                it.hasExtra("white_nail_art") -> {
                    whiteNailArt = it.getStringExtra("white_nail_art")
                    service.setText(whiteNailArt)
                }
                it.hasExtra("four_nail_art") -> {
                    fourNailArt = it.getStringExtra("four_nail_art")
                    service.setText(fourNailArt)
                }
                it.hasExtra("body_spa") -> {
                    bodySPA = it.getStringExtra("body_spa")
                    service.setText(bodySPA)
                }
                it.hasExtra("body_polishing") -> {
                    bodyPolishing = it.getStringExtra("body_polishing")
                    service.setText(bodyPolishing)
                }
                it.hasExtra("body_stream") -> {
                    bodyStream = it.getStringExtra("body_stream")
                    service.setText(bodyStream)
                }
                it.hasExtra("body_wax") -> {
                    bodyWax = it.getStringExtra("body_wax")
                    service.setText(bodyWax)
                }
            }
        }

        sendWhatsappBtn.setOnClickListener {
            val namestr = name.text.toString()
            val mobilestr = mobile.text.toString()
            val emailstr = email.text.toString()
            val servicestr = service.text.toString()
            val datestr = date.text.toString()

            val appointment = UserAppointmentHelperClass(namestr, mobilestr, emailstr, servicestr, datestr)

            val appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments")
            appointmentsRef.child(datestr).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(this@AppoinmentActivity, "This date is already booked. Please select another date.", Toast.LENGTH_SHORT).show()
                    } else {
                        appointmentsRef.child(datestr).setValue(appointment)
                        Toast.makeText(this@AppoinmentActivity, "Appointment booked successfully!", Toast.LENGTH_SHORT).show()

                        val phone = "+91 8446674235"
                        val msgstr = "Appoinment Alert \n \nName : $namestr \nMobile No :$mobilestr \nEmail :$emailstr \nService Name :$servicestr \nDate :$datestr"

                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse("http://api.whatsapp.com/send?phone=$phone&text=$msgstr")
                        startActivity(i)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@AppoinmentActivity, "Failed to check appointment availability.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(this@AppoinmentActivity,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, monthOfYear, dayOfMonth)

                // Check if the selected date is past, if yes, reset the date picker to current date
                if (selectedCalendar.before(calendar)) {
                    Toast.makeText(this@AppoinmentActivity, "Please select a current or future date", Toast.LENGTH_SHORT).show()
                    view.updateDate(calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
                } else {
                    date.setText("$dayOfMonth-${monthOfYear + 1}-$year")
                }
            }, year, month, day)

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }
}
