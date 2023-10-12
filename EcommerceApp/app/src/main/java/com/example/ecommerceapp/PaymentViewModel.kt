package com.example.ecommerceapp

import androidx.lifecycle.ViewModel

class PaymentViewModel : ViewModel() {
    fun isValidCardDetails(cardNumber: String, expiryDate:String, cvv: String): Boolean {
        return cardNumber.length==16 && expiryDate.length==4 && cvv.length==3
    }
    fun isCardNumberValid(cardNumber: String): Boolean {
        return cardNumber.matches(Regex("^\\d{16}$"))
    }

    fun isExpiryDateValid(expiryDate: String): Boolean {
        return expiryDate.matches(Regex("^\\d{4}$"))
    }

    fun isCvvValid(cvv: String): Boolean {
        return cvv.matches(Regex("^\\d{3}$"))
    }
}