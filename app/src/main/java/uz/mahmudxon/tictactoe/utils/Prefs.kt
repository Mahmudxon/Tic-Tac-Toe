package uz.mahmudxon.tictactoe.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import uz.mahmudxon.tictactoe.R
import javax.inject.Inject

class Prefs @Inject constructor(app: Context) {
    private val prefsName: String = app.getString(R.string.app_name).replace(" ", "")
    private var prefs: SharedPreferences

    val level = "level"
    val isJarvisWithX = "isJarvisWithX"
    val isCrossFirst = "isCrossFirst"

    init {
        prefs = app.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        Log.d("TTT", prefsName)
    }

    fun save(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun save(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun save(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun save(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    fun get(key: String, defValue: Int) = prefs.getInt(key, defValue)

    fun get(key: String, defValue: String) = prefs.getString(key, defValue)

    fun get(key: String, defValue: Float) = prefs.getFloat(key, defValue)

    fun get(key: String, defValue: Boolean) = prefs.getBoolean(key, defValue)

    fun get(key: String, defValue: Long) = prefs.getLong(key, defValue)
}
