package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignup = findViewById<MaterialButton>(R.id.btnSignup)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnSignup.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim().lowercase()
            val password = etPassword.text.toString().trim()

            when {
                name.isEmpty() || email.isEmpty() || password.isEmpty() ->
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()

                password.length < 6 ->
                    Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()

                UserPrefs.userExists(this, email) ->
                    Toast.makeText(this, "This email is already registered", Toast.LENGTH_SHORT).show()

                else -> {
                    UserPrefs.saveUser(this, name, email, password)
                    UserPrefs.setLoggedIn(this, email)
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            }
        }

        tvLogin.setOnClickListener { finish() }
    }
}