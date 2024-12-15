package com.example.registrationforms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment(private val credentialsManager: CredentialsManager) : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailFieldLayout = view.findViewById<TextInputLayout>(R.id.email_field)
        val emailField = view.findViewById<TextInputEditText>(R.id.email_field_text)
        val passwordFieldLayout = view.findViewById<TextInputLayout>(R.id.password_field)
        val passwordField = view.findViewById<TextInputEditText>(R.id.password_field_text)
        val nextButton = view.findViewById<Button>(R.id.next_button)
        val registerNowLabel = view.findViewById<TextView>(R.id.register)

        registerNowLabel.setOnClickListener {
            (activity as? MainActivity)?.navigateToFragment(RegisterFragment(credentialsManager))
        }

        nextButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            var isValid = true

            emailFieldLayout.error = null
            passwordFieldLayout.error = null

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
                if (credentialsManager.doesAccountExist(email)) {
                    if (credentialsManager.isPasswordCorrect(email, password)) {
                        Log.d("Login", "Login successful!")
                        (activity as? MainActivity)?.navigateToFragment(HomeFragment())
                    } else {
                        passwordFieldLayout.error = "Invalid password"
                    }
                } else {
                    emailFieldLayout.error = "Email not registered"
                }
            }
        }
    }
}
