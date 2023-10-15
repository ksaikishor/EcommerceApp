package com.example.ecommerceapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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

        username.addTextChangedListener {
            usernameLayout.error = null
            updateRegisterButtonState()
        }
        password.addTextChangedListener {
            passwordLayout.error = null
            updateRegisterButtonState()
        }
        emailId.addTextChangedListener {
            emailIdLayout.error = null
            updateRegisterButtonState()
        }
        address.addTextChangedListener {
            addressLayout.error = null
            updateRegisterButtonState()
        }

        loginBtn.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(LoginFragment.newInstance())
        }
    }

    private fun saveUserToSharedPreferences(user: User) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        Log.i("sharedPref from register fragment", "$sharedPreferences")
        val editor = sharedPreferences.edit()
        Log.i("username from register", "${user.username}")
        Log.i("password from register", "${user.password}")
        editor.putString("username", user.username)
        editor.putString("password", user.password)
        editor.putString("email", user.email)
        editor.putString("address", user.address)
        editor.apply()
    }

    fun updateRegisterButtonState() {
        registeredUser = User(
            username.text.toString(),
            password.text.toString(),
            emailId.text.toString(),
            address.text.toString()
        )
        registerBtn.isEnabled = false
        if (isUserValidated(registeredUser)) {
            registerBtn.isEnabled = true
            registerBtn.setOnClickListener {
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                val cartViewModel = (requireActivity() as MainActivity).cartViewModel
                cartViewModel?.clearCart()
                saveUserToSharedPreferences(registeredUser)
                Log.i("registered user is ", "$registeredUser")
                (activity as Communicator).passData(LoginFragment.newInstance())
            }
        }
    }

    fun isUserValidated(registeredUser: User): Boolean {
        var flag = false
        if (registerViewModel.isUserDataValid(registeredUser)) {
            flag = true
        } else {
            if (!registerViewModel.isUsernameValid(registeredUser.username)) {
                usernameLayout.error = "Invalid UserName"
            }
            if (!registerViewModel.isEmailValid(registeredUser.email)) {
                emailIdLayout.error = "Invalid Email"
            }
            if (!registerViewModel.isPasswordValid(registeredUser.password)) {
                passwordLayout.error = "Invalid password"
            }
            if (!registerViewModel.isAddressValid(registeredUser.address)) {
                addressLayout.error = "Invalid Address"
            }
        }
        return flag
    }

}