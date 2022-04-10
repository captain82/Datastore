package com.app.jetpackdatastore

import android.content.Context
import android.content.pm.ResolveInfo


class SharedPreferencesHelper constructor(private val context: Context) {

    private val preferences = context.getSharedPreferences(
        SharedPrefConstants.PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    private var editor = preferences.edit()

    fun setName() {
         editor.putString(SharedPrefConstants.NAME, "Ankit").apply()
    }

    fun getName(): String {
        return preferences.getString(SharedPrefConstants.NAME,"") ?: ""
    }

    fun setRole() {
        editor.putString(SharedPrefConstants.ROLE, "PE").apply()
    }

    fun getRole(): String {
        return preferences.getString(SharedPrefConstants.ROLE,"") ?: ""
    }


}