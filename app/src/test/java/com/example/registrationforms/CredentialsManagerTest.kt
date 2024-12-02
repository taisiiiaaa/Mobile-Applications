package com.example.registrationforms

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailEmpty("")

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

        assertEquals(true, credentialsManager.isPasswordEmpty("1234"))
    }

    //Test too short password
    @Test
    fun givenShortPasswordReturnFalse() {
        val credentialsManager = CredentialsManager()

        assertEquals(false, credentialsManager.isPasswordLongEnough("432"))
    }

    //test registration with valid email and password
    @Test
    fun givenValidEmailAndPassword_thenRegisterSuccessfully() {
        val credentialsManager = CredentialsManager()
        val result = credentialsManager.register("newuser@te.st", "password1234")
        assertEquals("Registration successful.", result)
    }

    //test registration with already registered email
    @Test
    fun givenUsedEmail_thenReturnError() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("usedemail@test.test", "password1234")
        val result = credentialsManager.register("usedemail@test.test", "1234password")
        assertEquals("Error: Email is already registered.", result)
    }

    //test case insensitivity in registration
    @Test
    fun givenUsedEmailWithDifferentCase_thenReturnError() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("usedemail@test.test", "password1234")
        val result = credentialsManager.register("USEDEMAIL@test.test", "1234password")

        assertEquals("Error: Email is already registered.", result)
    }

    //test account existence check after registration
    @Test
    fun givenNewAccount_thenAccountExists() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("test@test.test", "password1234")
        val accountExists = credentialsManager.doesAccountExist("test@test.test")

        assertEquals(true, accountExists)
    }

    //test account existence with case insensitivity 
    @Test
    fun givenAccountWithDifferentCase_thenAccountExists() {
        val credentialsManager = CredentialsManager()

        credentialsManager.register("test@test.test", "password1234")
        val accountExists = credentialsManager.doesAccountExist("TEST@test.test")

        assertEquals(true, accountExists)
    }
}