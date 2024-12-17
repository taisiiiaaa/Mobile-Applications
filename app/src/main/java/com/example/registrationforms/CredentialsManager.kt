package com.example.registrationforms

class CredentialsManager {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
            )

    private val credentials = mutableMapOf<String, String>(
        "test@te.st".lowercase() to "12345678"
    )

    fun isEmailEmpty(email: String): Boolean {
        return email.isNotEmpty()
    }

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }

    fun isPasswordEmpty(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun isPasswordLongEnough(password: String): Boolean {
        return password.length >= 8
    }

    fun register(email: String, password: String): String {
        val normalizedEmail = email.lowercase()

        return if (credentials.containsKey(normalizedEmail)) {
            "Error: Email is already registered."
        } else {
            credentials[normalizedEmail] = password
            "Registration successful."
        }
    }

    fun doesAccountExist(email: String): Boolean {
        return credentials.containsKey(email.lowercase())
    }

    fun isPasswordCorrect(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        return credentials[normalizedEmail] == password
    }
}