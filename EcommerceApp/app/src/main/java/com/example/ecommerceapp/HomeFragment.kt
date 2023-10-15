package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() ,ItemOnClick{

    companion object {
        fun newInstance(userName: String):HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString("username", userName)
            fragment.arguments = args
            return fragment
        }
    }
    private val productRepository: ProductRepositoryImplement = ProductRepositoryImplement()
    private val homeViewModel : HomeViewModel by viewModels{
        HomeViewModelFactory(productRepository)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter
    private var productList: MutableList<Product> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var cartFromHome : Button
    private lateinit var welcomeNote : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartViewModel = (requireActivity() as MainActivity).cartViewModel
        welcomeNote = view.findViewById(R.id.welcomeNote)
        welcomeNote.text = "Welcome "+arguments?.getString("username")
        recyclerView = view.findViewById(R.id.recyclerViewHome)
        recyclerView.layoutManager =
            GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        adapter = cartViewModel?.let { HomeAdapter(productList,this, it) }!!
        recyclerView.adapter = adapter
        progressBar = view.findViewById(R.id.progressBar)
        homeViewModel.fetchProducts()

        homeViewModel.productList.observe(viewLifecycleOwner) { products ->
            if (products != null) {
                productList.clear()
                productList.addAll(products)
                adapter.notifyDataSetChanged()
            }
        }
        homeViewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            showProgressBar(loading)
        }

        cartFromHome = view.findViewById(R.id.cartFromHome)
        val cartItemCount = cartViewModel?.getCartItemCount()
        cartFromHome.text ="("+cartItemCount+")"
        cartFromHome.setOnClickListener {
            (activity as Communicator).passData(CartFragment.newInstance())
        }

    }

    private fun showProgressBar(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun itemClick(index: Int,cartViewModel: CartViewModel) {
        val id = productList[index-1].id
        Log.i("clicked on: ","$id")
        (activity as MainActivity).passData(ProductDetailFragment.newInstance(id))
    }


}