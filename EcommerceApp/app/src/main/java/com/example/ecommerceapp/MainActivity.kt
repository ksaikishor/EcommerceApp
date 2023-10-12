package com.example.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() ,Communicator{
    var cartViewModel: CartViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        replaceFragment(LoginFragment.getInstance())
    }

    fun replaceFragment(instance: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, instance)
            .addToBackStack(null).commit()
    }

    override fun passData(instance: Fragment) {
        replaceFragment(instance)
    }


}