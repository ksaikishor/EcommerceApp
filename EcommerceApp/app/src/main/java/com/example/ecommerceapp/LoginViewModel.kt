package com.example.ecommerceapp

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun isValid(savedUser :String ,savedPwd : String,userName : String, pwd : String ):Boolean{
        return if(!savedUser.isNullOrEmpty() && !savedPwd.isNullOrEmpty() && !userName.isNullOrEmpty() && !pwd.isNullOrEmpty()) {
            savedUser == userName && savedPwd == pwd
        } else{
            false
        }
    }
}