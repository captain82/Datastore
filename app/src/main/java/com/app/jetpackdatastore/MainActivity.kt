package com.app.jetpackdatastore

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceDataStore
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = SharedPreferencesHelper(this)
        //sharedPref.dataStoreName = "AK"
        //sharedPref.dataStoreRole = "PEs"

        val textView = findViewById<TextView>(R.id.text)
        textView.text = "Name: ${sharedPref.dataStoreName} \nRole: ${sharedPref.dataStoreRole}"
    }
}