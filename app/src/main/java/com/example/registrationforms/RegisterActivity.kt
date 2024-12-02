package com.example.registrationforms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.registrationforms.CredentialsManager

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val credentialsManager = CredentialsManager()


        val emailRegisterLayout = findViewById<TextInputLayout>(R.id.register_email_field)
        val emailRegisterText = findViewById<TextInputEditText>(R.id.register_email_text)
        val passwordRegisterLayout = findViewById<TextInputLayout>(R.id.register_password_field)
        val passwordRegisterText = findViewById<TextInputEditText>(R.id.register_password_text)
        val nextRegisterButton = findViewById<Button>(R.id.register_button_next)

        val logInLabel = findViewById<TextView>(R.id.log_in)

        nextRegisterButton.setOnClickListener {
            val email = emailRegisterText.text.toString()
            val password = passwordRegisterText.text.toString()

            var isValid = true

            emailRegisterLayout.error = null
            emailRegisterLayout.isErrorEnabled = false
            passwordRegisterLayout.error = null
            passwordRegisterLayout.isErrorEnabled = false

            if (email.isEmpty()) {
                emailRegisterLayout.error = "Email cannot be empty"
                isValid = false
            } else if (!credentialsManager.isEmailValid(email)) {
                emailRegisterLayout.error = "Invalid email format"
                isValid = false
            }

            if (password.isEmpty()) {
                passwordRegisterLayout.error = "Password cannot be empty"
                isValid = false
            } else if (!credentialsManager.isPasswordLongEnough(password)) {
                passwordRegisterLayout.error = "Password must be at least 8 characters"
                isValid = false
            }

            val result = credentialsManager.register(email, password)

            if (isValid) {
                if (result == "Registration successful.") {
                    Log.d("Onboarding", "Clicked log in label")
                    val goToLogInIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(goToLogInIntent)
                } else {
                    emailRegisterLayout.error = "Already used email"
                }
            }

        }

        logInLabel.setOnClickListener {
            Log.d("Onboarding", "Clicked log in label")

            val goToLogInIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(goToLogInIntent)
            finish()
        }
    }
}
