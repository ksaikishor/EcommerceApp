package com.example.ecommerceapp

import junit.extensions.TestSetup
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RegisterViewModelTest {
    private lateinit var registerViewModel: RegisterViewModel
    @Before
    fun setup(){
        registerViewModel = RegisterViewModel()
    }
    @Test
    fun `is valid registration`(){
        val user = User("Kishore","12345","sai@gmail.com","Hyderabad")
        val result = registerViewModel.isUserDataValid(user)
        Assert.assertEquals(true,result)
    }
    @Test
    fun `validating empty email`(){
        val result =registerViewModel.isValidEmail("")
        Assert.assertEquals(false,result)
    }
    @Test
    fun `validating email `(){
        val result = registerViewModel.isValidEmail("Sai@gmail.com")
        Assert.assertEquals(true,result)
    }

}
