package com.example.ecommerceapp

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

class RegisterFragment : Fragment() {
    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var registerBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var emailId: TextInputEditText
    private lateinit var address: TextInputEditText
    private lateinit var registeredUser: User
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var emailIdLayout: TextInputLayout
    private lateinit var addressLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBtn = view.findViewById(R.id.Register)
        loginBtn = view.findViewById(R.id.LoginAgain)
        username = view.findViewById(R.id.usernameReg)
        password = view.findViewById(R.id.passwordReg)
        emailId = view.findViewById(R.id.email)
        address = view.findViewById(R.id.address)
        usernameLayout = view.findViewById(R.id.usernameRegInputLayout)
        passwordLayout = view.findViewById(R.id.passwordLayout)
        emailIdLayout = view.findViewById(R.id.emailLayout)
        addressLayout = view.findViewById(R.id.addressLayout)

        setupTextChangeListeners()

        loginBtn.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(LoginFragment.newInstance())
        }
    }

    private fun setupTextChangeListeners() {
        username.addTextChangedListener { text ->
            val userName = text.toString()
            usernameLayout.error = if (userName.isNotEmpty() && !registerViewModel.isUsernameValid(userName)) "Invalid Username" else null
            updateRegisterButtonState()
        }

        password.addTextChangedListener { text ->
            val pwd = text.toString()
            passwordLayout.error = if (pwd.isNotEmpty() && !registerViewModel.isPasswordValid(pwd)) "Invalid Password" else null
            updateRegisterButtonState()
        }

        emailId.addTextChangedListener { text ->
            val email = text.toString()
            emailIdLayout.error = if (email.isNotEmpty() && !registerViewModel.isEmailValid(email)) "Invalid Email" else null
            updateRegisterButtonState()
        }

        address.addTextChangedListener { text ->
            val userAddress = text.toString()
            addressLayout.error = if (userAddress.isNotEmpty() && !registerViewModel.isAddressValid(userAddress)) "Invalid Address" else null
            updateRegisterButtonState()
        }
    }

    private fun saveUserToSharedPreferences(user: User) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", user.username)
        editor.putString("password", user.password)
        editor.putString("email", user.email)
        editor.putString("address", user.address)
        editor.apply()
    }

    private fun updateRegisterButtonState() {
        registeredUser = User(
            username.text.toString(),
            password.text.toString(),
            emailId.text.toString(),
            address.text.toString()
        )
        registerBtn.isEnabled = isUserValidated(registeredUser)
    }

    private fun isUserValidated(registeredUser: User): Boolean {
        return registerViewModel.isUserDataValid(registeredUser)
    }
}
