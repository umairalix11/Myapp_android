package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = UserPrefs.getLoggedInEmail(this) ?: ""
        val name = UserPrefs.getName(this, email)

        findViewById<TextView>(R.id.tvWelcome).text = "Welcome, $name"

        findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            UserPrefs.logout(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }
}