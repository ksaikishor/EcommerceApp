package com.example.ecommerceapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance(productId: Int): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val args = Bundle()
            args.putInt("productId", productId)
            fragment.arguments = args
            return fragment
        }
    }
    private val productRepository: ProductRepositoryImplement = ProductRepositoryImplement()
    private val productViewModel : ProductDetailViewModel by viewModels{
        ProductViewDetailModelFactory(productRepository)
    }
    private lateinit var productImageView: ImageView
    private lateinit var productDescriptionTextView: TextView
    private lateinit var backButton: ImageView
    private lateinit var cartButton: Button
    private lateinit var rating: TextView
    private lateinit var productPrice : TextView
    private lateinit var addToCart : Button
    private lateinit var progressBar : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productImageView = view.findViewById(R.id.imageView)
        productDescriptionTextView = view.findViewById(R.id.product_description)
        backButton = view.findViewById(R.id.product_detail_backButton)
        cartButton = view.findViewById(R.id.product_detail_cart)
        rating = view.findViewById(R.id.product_rating)
        productPrice = view.findViewById(R.id.productPrice)
        addToCart = view.findViewById(R.id.addToCart)

        val cartViewModel = (requireActivity() as MainActivity).cartViewModel
        cartButton.text ="("+cartViewModel?.cartList?.size+")"

        val productId = arguments?.getInt("productId") ?: -1
        productViewModel.fetchProduct(productId)
        productViewModel.product.observe(viewLifecycleOwner){product->
            productDescriptionTextView.text = "Description:"+"\n"+product.description
            productPrice.text = "$ "+product.price.toString()

            rating.text =product.rating?.rate.toString()+" Rating"
            Glide.with(view.context).load(product.image).into(productImageView)
        }
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        addToCart.setOnClickListener {
            Toast.makeText(context,"Item Added to Cart",Toast.LENGTH_SHORT).show()
            productViewModel.product.observe(viewLifecycleOwner) { product ->
                val cartItem = Product(productId, product.title, product.price,product.description,product.category, product.image,product.rating)
                cartViewModel?.addItemToCart(cartItem)
                cartButton.text ="("+cartViewModel?.cartList?.size+")"
            }
        }

        cartButton.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(CartFragment.newInstance())
        }
        progressBar = view.findViewById(R.id.productProgressBar)
        productViewModel.isLoadingProduct.observe(viewLifecycleOwner) { loading ->
            showProgressBar(loading)
        }

    }
    private fun showProgressBar(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

}