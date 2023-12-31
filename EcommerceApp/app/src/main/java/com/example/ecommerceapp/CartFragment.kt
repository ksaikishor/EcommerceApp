package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment(), ItemOnClick {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private var productItemList: MutableList<CartItem> = mutableListOf()
    private lateinit var checkout: Button
    private lateinit var backButton: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val cartViewModel = (requireActivity() as MainActivity).cartViewModel
        cartAdapter = cartViewModel?.let { CartAdapter(productItemList, this, it) }!!
        recyclerView.adapter = cartAdapter
        cartViewModel?.cartList?.observe(viewLifecycleOwner) { cartItemList ->
            productItemList.clear()
            if (cartItemList != null) {
                productItemList.addAll(cartItemList)
            }
            cartAdapter.notifyDataSetChanged()
        }

        backButton = view.findViewById(R.id.backButtonCart)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        checkout = view.findViewById(R.id.checkout)
        checkout.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(PaymentFragment.newInstance())
        }

    }

    override fun itemClick(index: Int,cartViewModel: CartViewModel) {
        Log.i("clicked on", "$index")
        cartViewModel.removeItemFromCart(index)
        cartAdapter.notifyItemRemoved(index)
    }

}