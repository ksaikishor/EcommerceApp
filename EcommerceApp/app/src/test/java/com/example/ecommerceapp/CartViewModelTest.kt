package com.example.ecommerceapp

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.CartItem
import com.example.ecommerceapp.CartViewModel
import com.example.ecommerceapp.Product
import com.example.ecommerceapp.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CartViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cartViewModel: CartViewModel


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        sharedPreferences = mock(SharedPreferences::class.java)
        val editor = mock(SharedPreferences.Editor::class.java)
        `when`(editor.putString(any(), any())).thenReturn(editor)
        `when`(sharedPreferences.edit()).thenReturn(editor)

        cartViewModel = CartViewModel()

        `when`(context.getSharedPreferences("cart_preferences", Context.MODE_PRIVATE)).thenReturn(sharedPreferences)

        cartViewModel.initSharedPreferences(context)
    }



    @Test
    fun addItemToCart() {
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        val count =1
        val cartItem1 = CartItem(product,count)

        cartViewModel.addItemToCart(cartItem1)

        val cartList = cartViewModel.cartList.value
        assertNotNull(cartList)
        assertEquals(1, cartList?.size)
    }

    @Test
    fun removeItemFromCart() {
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        val count =1
        val cartItem1 = CartItem(product,count)

        cartViewModel.addItemToCart(cartItem1)

        cartViewModel.removeItemFromCart(0)

        val cartList = cartViewModel.cartList.value
        assertNotNull(cartList)
        assertEquals(0, cartList?.size)
    }

    @Test
    fun clearCart() {
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        val count =1
        val cartItem1 = CartItem(product,count)

        cartViewModel.addItemToCart(cartItem1)

        cartViewModel.clearCart()

        val cartList = cartViewModel.cartList.value
        assertNotNull(cartList)
        assertEquals(0, cartList?.size)
    }

    @Test
    fun getCartItemCount() {
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        val count =1
        val cartItem1 = CartItem(product,count)

        val product2 = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        val count2 =1
        val cartItem2 = CartItem(product2,count2)

        cartViewModel.addItemToCart(cartItem1)

        val itemCount = cartViewModel.getCartItemCount()
        assertEquals(1, itemCount)
    }
}
