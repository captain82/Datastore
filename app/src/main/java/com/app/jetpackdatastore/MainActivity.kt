package com.app.jetpackdatastore

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = SharedPreferencesHelper(this)
        sharedPref.setName()
        sharedPref.setRole()

        val textView = findViewById<TextView>(R.id.text)
        textView.text = "Name: ${sharedPref.getName()} \nRole: ${sharedPref.getRole()}"
    }
}