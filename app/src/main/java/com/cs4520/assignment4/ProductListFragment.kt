package com.cs4520.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cs4520.assignment4.adapters.ProductAdapter
import com.cs4520.assignment4.databinding.FragmentProductListBinding
import com.cs4520.assignment4.viewmodels.ProductListViewModel
import com.cs4520.assignment4.viewmodels.ViewModelProvider

class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels() { ViewModelProvider.Factory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        val productAdapter = ProductAdapter()

        binding.productList.adapter = productAdapter
        viewModel.fetchProducts()

        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                ProductListViewModel.State.SUCCESS -> {
                    productAdapter.submitList(viewModel.products.value)
                    if (viewModel.products.value == null || viewModel.products.value!!.isEmpty()) {
                        binding.noProductMessage.visibility = View.VISIBLE
                    } else {
                        binding.noProductMessage.visibility = View.GONE
                    }
                    binding.progressBar.visibility = View.GONE
                }

                ProductListViewModel.State.FAIL -> {
                    binding.progressBar.visibility = View.GONE
                    binding.noProductMessage.visibility = View.GONE
                    displayToast(viewModel.errorMessage)
                }

                ProductListViewModel.State.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.noProductMessage.visibility = View.GONE
                }
            }
        }

        return binding.root
    }

    private fun displayToast(message: String?) {
        if (message != null) {
            val appContext = context?.applicationContext ?: return
            Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
        }
    }
}