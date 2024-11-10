package com.example.registrationforms

import android.content.Intent
import android.credentials.RegisterCredentialDescriptionRequest
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.RegisterReceiverFlags
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registerNowLabel = findViewById<TextView>(R.id.register)

        registerNowLabel.setOnClickListener {
            Log.d("Onboarding", "Clicked register now label")

            val goToRegisterIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(goToRegisterIntent)
        }
    }
}

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

        val logInLabel = findViewById<TextView>(R.id.log_in)

        logInLabel.setOnClickListener {
            Log.d("Onboarding", "Clicked log in label")

            val goToLogInIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(goToLogInIntent)
        }
    }
}
