// Put your package name here. Check your activity for reference.
package com.example.xyz

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("")

        assertEquals(false, isEmailValid)
    }

    // Test wrong email format
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("wrong email")

        assertEquals(false, isEmailValid)
    }

    // Test proper email
    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()

        assertEquals(true, credentialsManager.isEmailValid("email@te.st"))
    }

    // Test empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        assertEquals(false, credentialsManager.isPasswordEmpty(""))
    }

    // Test filled password
    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()

        assertEquals(true, credentialsManager.isPasswordEmpty("123"))
    }
}