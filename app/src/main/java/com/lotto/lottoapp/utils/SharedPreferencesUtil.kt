package com.lotto.lottoapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class SharedPreferencesUtil(context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val gson = Gson()

    fun saveData(key: String, data: Any) {
        val jsonString = gson.toJson(data)
        with(sharedPreferences.edit()) {
            putString(key, jsonString)
            apply()
        }
    }

    inline fun <reified T> loadData(key: String, defaultValue: T? = null): T {
        val jsonString = sharedPreferences.getString(key, null)
        return try {
            if (jsonString != null) {
                gson.fromJson(jsonString, T::class.java)
            } else {
                defaultValue
                    ?: throw IllegalArgumentException("Default value must be provided for non-nullable types.")
            }
        } catch (e: JsonSyntaxException) {
            // Handle the exception, log it, or provide a default value
            defaultValue ?: throw IllegalArgumentException("Error parsing JSON for key: $key", e)
        }
    }

    fun deleteData(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }

}
