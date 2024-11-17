// Put your package name here. Check your activity for reference.
package com.example.xyz

class CredentialsManager {
    private val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
            )

    fun isEmailValid(email: String): Boolean {
        return Regex(emailPattern).matches(email)
    }

    fun isPasswordEmpty(password: String): Boolean {
        return password.isNotEmpty()
    }
}