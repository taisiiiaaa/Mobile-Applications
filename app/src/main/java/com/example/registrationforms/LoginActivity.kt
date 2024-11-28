package com.example.registrationforms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registrationforms.CredentialsManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private val emailValid = "test@te.st"
    private val passwordValid = "12345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val credentialsManager = CredentialsManager()

        val emailFieldLayout = findViewById<TextInputLayout>(R.id.email_field)
        val emailField = findViewById<TextInputEditText>(R.id.email_field_text)
        val passwordFieldLayout = findViewById<TextInputLayout>(R.id.password_field)
        val passwordField = findViewById<TextInputEditText>(R.id.password_field_text)
        val nextButton = findViewById<Button>(R.id.next_button)

        val registerNowLabel = findViewById<TextView>(R.id.register)

        registerNowLabel.setOnClickListener {
            Log.d("Onboarding", "Clicked register now label")

            val goToRegisterIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(goToRegisterIntent)
        }

        nextButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            var isValid = true

            emailFieldLayout.error = null
            emailFieldLayout.isErrorEnabled = false
            passwordFieldLayout.error = null
            passwordFieldLayout.isErrorEnabled = false

            if (email.isEmpty()) {
                emailFieldLayout.error = "Email cannot be empty"
                isValid = false
            } else if (!credentialsManager.isEmailValid(email)) {
                emailFieldLayout.error = "Invalid email format"
                isValid = false
            }

            if (password.isEmpty()) {
                passwordFieldLayout.error = "Password cannot be empty"
                isValid = false
            } else if (!credentialsManager.isPasswordLongEnough(password)) {
                passwordFieldLayout.error = "Password must be at least 8 characters"
                isValid = false
            }

            if (isValid) {
                if (email == emailValid && password == passwordValid) {
                    val goToMainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(goToMainIntent)
                    finish()
                } else {
                    passwordFieldLayout.error = "Invalid email or password"
                }
            }
        }
    }
}


