package com.app.jetpackdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException

private val Context._dataStore by preferencesDataStore(
    name = SharedPrefConstants.DATASTORE_NAME,
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, SharedPrefConstants.PREFERENCES_NAME))
    }
)
class SharedPreferencesHelper constructor(private val context: Context) {

    private val preferences = context.getSharedPreferences(
        SharedPrefConstants.PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    val dataStore: DataStore<Preferences> = context._dataStore

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

    var dataStoreName: String
        get() = runBlocking{
            withContext(Dispatchers.Default){
                dataStore.getValueFlow(stringPreferencesKey(SharedPrefConstants.NAME), "").first()
            }
        }

        set(value) = runBlocking {
            withContext(Dispatchers.Default){
                dataStore.edit {
                    it[stringPreferencesKey(SharedPrefConstants.NAME)] = value
                }
            }
        }

    var dataStoreRole: String
        get() = runBlocking{
            withContext(Dispatchers.Default) {
                dataStore.getValueFlow(stringPreferencesKey(SharedPrefConstants.ROLE), "").first()
            }
        }

        set(value) = runBlocking {
            withContext(Dispatchers.Default){
                dataStore.edit {
                    it[stringPreferencesKey(SharedPrefConstants.ROLE)] = value
                }
            }
        }

    fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

}