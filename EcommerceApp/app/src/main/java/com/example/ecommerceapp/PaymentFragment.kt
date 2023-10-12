package com.example.ecommerceapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class PaymentFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentFragment()
    }

    private val paymentViewModel: PaymentViewModel by viewModels()
    private lateinit var cardNumberLayout: TextInputLayout
    private lateinit var cardNumber: TextInputEditText
    private lateinit var expiryDateLayout: TextInputLayout
    private lateinit var expiryDate: TextInputEditText
    private lateinit var cvvLayout: TextInputLayout
    private lateinit var cvv: TextInputEditText
    private lateinit var payment: Button
    private lateinit var backButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardNumberLayout = view.findViewById(R.id.cardNumberInputLayout)
        cardNumber = view.findViewById(R.id.cardNumberEditText)
        expiryDateLayout = view.findViewById(R.id.expiryDateInputLayout)
        expiryDate = view.findViewById(R.id.expiryDateEditText)
        cvvLayout = view.findViewById(R.id.cvvInputLayout)
        cvv = view.findViewById(R.id.cvvEditText)
        payment = view.findViewById(R.id.paymentButton)

        cardNumber.addTextChangedListener {
            cardNumberLayout.error = null
            updatePaymentButtonState()
        }
        expiryDate.addTextChangedListener {
            expiryDateLayout.error = null
            updatePaymentButtonState()
        }
        cvv.addTextChangedListener {
            cvvLayout.error = null
            updatePaymentButtonState()
        }

        backButton = view.findViewById(R.id.backButtonPayment)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    private fun shouldEnableSubmit(cardNumberText: String, expiryDateText: String, cvvText: String): Boolean {
        var flag = false

        if (paymentViewModel.isValidCardDetails(cardNumberText, expiryDateText, cvvText)) {
            flag = true
        } else {
            if (!paymentViewModel.isCardNumberValid(cardNumberText)) {
                cardNumberLayout.error = "Invalid card number"
            }
            if (!paymentViewModel.isExpiryDateValid(expiryDateText)) {
                expiryDateLayout.error = "Invalid expiry date"
            }
            if (!paymentViewModel.isCvvValid(cvvText)) {
                cvvLayout.error = "Invalid CVV"
            }
        }
        return flag
    }
    private fun updatePaymentButtonState(){
        val cardNumberText = cardNumber.text.toString()
        val expiryDateText = expiryDate.text.toString()
        val cvvText = cvv.text.toString()
        payment.isEnabled = false
        if(shouldEnableSubmit(cardNumberText,expiryDateText,cvvText)) {
            payment.isEnabled = true
            payment.setOnClickListener {
                val cartViewModel = (requireActivity() as MainActivity).cartViewModel
                cartViewModel?.clearCart()
                (activity as Communicator).passData(PaymentSuccessFragment.newInstance())
            }
        }
    }
}
