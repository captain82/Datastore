package com.app.jetpackdatastore

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceDataStore
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var sharedPref: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = SharedPreferencesHelper(this)
        textView = findViewById(R.id.text)

        getDataFromSharedPrefAsync()
    }

    private fun getDataFromSharedPrefSync() {
        textView.text = "Name: ${sharedPref.nameFromDataStore} \nRole: ${sharedPref.roleFromDataStore}"
    }

    private fun getDataFromSharedPrefAsync() {
        lifecycleScope.launch(Dispatchers.IO) {
            Log.i("activityThread","${Thread.currentThread().name}")
            val name = sharedPref.getNameFromDS()
            val role = sharedPref.getRoleFromDS()
            withContext(Dispatchers.Main){
                Log.i("activityThread","${Thread.currentThread().name}")
                textView.text =  "Name: ${name} \nRole: ${role}"
            }
        }
    }
}