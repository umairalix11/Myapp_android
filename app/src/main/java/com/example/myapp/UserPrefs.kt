package com.example.myapp

import android.content.Context

object UserPrefs {
    private const val FILE = "myapp_prefs"
    private const val KEY_LOGGED_IN = "logged_in_email"

    private fun prefs(c: Context) = c.getSharedPreferences(FILE, Context.MODE_PRIVATE)

    fun userExists(c: Context, email: String): Boolean =
        prefs(c).contains("pass_$email")

    fun saveUser(c: Context, name: String, email: String, password: String) {
        prefs(c).edit()
            .putString("name_$email", name)
            .putString("pass_$email", password)
            .apply()
    }

    fun checkLogin(c: Context, email: String, password: String): Boolean =
        prefs(c).getString("pass_$email", null) == password

    fun getName(c: Context, email: String): String =
        prefs(c).getString("name_$email", "") ?: ""

    fun setLoggedIn(c: Context, email: String) {
        prefs(c).edit().putString(KEY_LOGGED_IN, email).apply()
    }

    fun getLoggedInEmail(c: Context): String? =
        prefs(c).getString(KEY_LOGGED_IN, null)

    fun logout(c: Context) {
        prefs(c).edit().remove(KEY_LOGGED_IN).apply()
    }
}