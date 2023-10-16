package com.example.ecommerceapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
        @SuppressLint("StaticFieldLeak")
        private var instance: LoginFragment? = null
        fun getInstance(): LoginFragment {
            if (instance == null) {
                instance = LoginFragment()
            }
            return instance!!
        }
    }

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout

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
        usernameLayout = view.findViewById(R.id.usernamelayout)
        passwordLayout = view.findViewById(R.id.passwordlayout)

        loginButton = view.findViewById(R.id.Login)
        registerButton = view.findViewById(R.id.Register)

        setupTextChangeListeners()

        loginButton.setOnClickListener {
            val userName = username.text.toString()
            val pwd = password.text.toString()

            if (loginUser(userName, pwd)) {
                (activity as Communicator).passData(HomeFragment.newInstance(userName))
            } else {
                Toast.makeText(context, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            (activity as Communicator).passData(RegisterFragment.newInstance())
        }
    }

    private fun setupTextChangeListeners() {
        username.addTextChangedListener { text ->
            val userName = text.toString()
            usernameLayout.error = if (userName.isNotEmpty() && !loginViewModel.isUsernameValid(userName)) "Invalid Username" else null
        }

        password.addTextChangedListener { text ->
            val pwd = text.toString()
            passwordLayout.error = if (pwd.isNotEmpty() && !loginViewModel.isPasswordValid(pwd)) "Invalid Password" else null
        }
    }

    private fun loginUser(username: String, password: String): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        return loginViewModel.isValid(savedUsername ?: "", savedPassword ?: "", username, password)
    }
}
