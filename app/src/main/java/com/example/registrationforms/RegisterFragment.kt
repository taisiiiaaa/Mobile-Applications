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

class RegisterFragment(private val credentialsManager: CredentialsManager) : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailRegisterLayout = view.findViewById<TextInputLayout>(R.id.register_email_field)
        val emailRegisterText = view.findViewById<TextInputEditText>(R.id.register_email_text)
        val passwordRegisterLayout = view.findViewById<TextInputLayout>(R.id.register_password_field)
        val passwordRegisterText = view.findViewById<TextInputEditText>(R.id.register_password_text)
        val nextRegisterButton = view.findViewById<Button>(R.id.register_button_next)
        val logInLabel = view.findViewById<TextView>(R.id.log_in)

        nextRegisterButton.setOnClickListener {
            val email = emailRegisterText.text.toString()
            val password = passwordRegisterText.text.toString()

            emailRegisterLayout.error = null
            passwordRegisterLayout.error = null

            if (!credentialsManager.isEmailValid(email)) {
                emailRegisterLayout.error = "Invalid email format"
            } else if (!credentialsManager.isPasswordLongEnough(password)) {
                passwordRegisterLayout.error = "Password must be at least 8 characters"
            } else {
                val result = credentialsManager.register(email, password)
                if (result == "Registration successful.") {
                    Log.d("Register", "Registration successful")
                    (activity as? MainActivity)?.navigateToFragment(LoginFragment(credentialsManager))
                } else {
                    emailRegisterLayout.error = "Email already registered"
                }
            }
        }

        logInLabel.setOnClickListener {
            (activity as? MainActivity)?.navigateToFragment(LoginFragment(credentialsManager))
        }
    }
}
