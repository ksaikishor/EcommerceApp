package com.example.ecommerceapp

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartViewModelTest {
    private lateinit var cartViewModel : CartViewModel
    @Before
    fun setUp(){
        cartViewModel = CartViewModel()
    }
    @Test
    fun `adding item to cart`(){
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        cartViewModel.addItemToCart(product)
        val size = cartViewModel.getCartItemCount()
        Assert.assertEquals(1,size)
    }
    @Test
    fun `clearCart`(){
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        cartViewModel.addItemToCart(product)
        cartViewModel.clearCart()
        val size = cartViewModel.getCartItemCount()
        Assert.assertEquals(0,size)

    }
    @Test
    fun `remove item from cart`(){
        val product = Product(1,"Shirt",10.0,"quality shirt","Mens Clothing","www.exampleImage.com",
            Rating(4.2,100)
        )
        cartViewModel.addItemToCart(product)
        cartViewModel.removeItemFromCart(0)
        val size = cartViewModel.getCartItemCount()
        Assert.assertEquals(0,size)
    }
}