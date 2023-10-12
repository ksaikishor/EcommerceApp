package com.example.ecommerceapp

import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {
    fun isUserDataValid(user : User):Boolean{
        return user.username.length>=3 && user.password.length>=4 && user.address.length>=3 && isEmailValid(user.email)
    }

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun isEmailValid(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }

    fun isUsernameValid(username: String): Boolean {
        return username.length>=3
    }

    fun isPasswordValid(password: String): Boolean {
        return password.matches(Regex("^\\d{5}$"))
    }

    fun isAddressValid(address: String): Boolean {
        return address.matches(Regex("^\\d{3}$"))
    }

}