package com.app.jetpackdatastore

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var sharedPref: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = SharedPreferencesHelper(this)
        textView = findViewById(R.id.text)
        sharedPref.setName()
        sharedPref.setRole()

        textView.text = "Name: ${sharedPref.getName()} \nRole: ${sharedPref.getRole()}"

    }
}