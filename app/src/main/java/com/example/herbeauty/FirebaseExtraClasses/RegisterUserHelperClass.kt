package com.example.herbeauty.FirebaseExtraClasses

// Data class for better readability and Firebase compatibility
data class RegisterUserHelperClass(
    var fullName: String? = null,
    var username: String? = null,
    var password: String? = null,
    var mobileNo: String? = null,
    var eMailId: String? = null
)
