package com.example.ecommerceapp

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PaymentViewModelTest {
    private lateinit var paymentViewModel: PaymentViewModel
    @Before
    fun setUp(){
        paymentViewModel = PaymentViewModel()
    }
    @Test
    fun `test valid card details`(){
        val cardNumber = "1234567891234567"
        val expiryDate = "1234"
        val cvv = "123"
        val result = paymentViewModel.isValidCardDetails(cardNumber,expiryDate,cvv)
        Assert.assertEquals(true,result)
    }
    @Test
    fun `test invalid card details`(){
        val cardNumber = "123456789123"
        val expiryDate = "12"
        val cvv = ""
        val result = paymentViewModel.isValidCardDetails(cardNumber,expiryDate,cvv)
        Assert.assertEquals(false,result)
    }
}