package com.bws.officeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile2)
        supportActionBar?.hide()
    }
}