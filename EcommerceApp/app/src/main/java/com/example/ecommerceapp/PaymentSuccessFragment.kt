package com.example.ecommerceapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels

class PaymentSuccessFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentSuccessFragment()
    }

    private val viewModel: PaymentSuccessViewModel by viewModels()
    private lateinit var goHome : Button
    private lateinit var thanksNote : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val user = sharedPreferences.getString("username", "")
        thanksNote = view.findViewById(R.id.ThanksNote)
        thanksNote.text = "Thanks "+"$user"+" for Ordering and Payment"
        goHome = view.findViewById(R.id.goHome)
        goHome.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(HomeFragment.newInstance(user?:""))
        }
    }

}