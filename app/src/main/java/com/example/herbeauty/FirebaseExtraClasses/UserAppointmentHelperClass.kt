package com.example.herbeauty.FirebaseExtraClasses

class UserAppointmentHelperClass {
    var name: String? = null
    var mobile: String? = null
    var email: String? = null
    var service: String? = null
    var date: String? = null

    constructor()
    constructor(name: String?, mobile: String?, email: String?, service: String?, date: String?) {
        this.name = name
        this.mobile = mobile
        this.email = email
        this.service = service
        this.date = date
    }
}
