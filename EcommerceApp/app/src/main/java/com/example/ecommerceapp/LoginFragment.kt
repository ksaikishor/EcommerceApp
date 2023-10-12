package com.example.ecommerceapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
        private var instance: LoginFragment? = null
        fun getInstance(): LoginFragment {
            if (instance == null) {
                instance = LoginFragment()
            }
            return instance!!
        }
    }

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var loginButton : Button
    private lateinit var registerButton : Button
    private lateinit var username : EditText
    private lateinit var password : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        loginButton = view.findViewById(R.id.Login)
        registerButton = view.findViewById(R.id.Register)
        loginButton.setOnClickListener {
            val userName = username.text.toString()
            Log.i("username typed is","${userName}")

            val pwd = password.text.toString()
            Log.i("password typed is","${pwd}")
            if(loginUser(userName,pwd)){
                (activity as Communicator).passData(HomeFragment.newInstance(userName))
            }
            else{
                Toast.makeText(context,"Please enter Valid credentials",Toast.LENGTH_SHORT).show()
            }
        }
        registerButton.setOnClickListener {
            (activity as Communicator).passData(RegisterFragment.newInstance())
        }

    }
    private fun loginUser(username: String, password: String):Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        Log.i("sharedPref from login fragment","$sharedPreferences")
        val savedUsername = sharedPreferences.getString("username", "")
        Log.i("saved user is","$savedUsername")
        val savedPassword = sharedPreferences.getString("password", "")
        Log.i("saved pwd is","$savedPassword")
        val isValidated = loginViewModel.isValid(savedUsername?:"",savedPassword?:"",username,password)
        Log.i("isValidated","$isValidated")
        return isValidated
    }

}