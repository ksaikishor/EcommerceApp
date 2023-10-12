package com.example.ecommerceapp

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
    private lateinit var  loginViewModel : LoginViewModel
    @Before
    fun setUp(){
        loginViewModel = LoginViewModel()
    }
    @Test
    fun `is valid user or not`(){
        val result =loginViewModel.isValid("Kishore","12345","Kishore","12345")
        Assert.assertEquals(true,result)
    }
    @Test
    fun `validating false user details`(){
        val result = loginViewModel.isValid("SaiKishore","12345","Saaai","11111")
        Assert.assertEquals(false,result)
    }
    @Test
    fun `validating empty user details`(){
        val result = loginViewModel.isValid("","","","")
        Assert.assertEquals(false,result)
    }
}