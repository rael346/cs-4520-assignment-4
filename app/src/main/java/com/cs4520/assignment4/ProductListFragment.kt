package com.cs4520.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cs4520.assignment4.adapters.ProductAdapter
import com.cs4520.assignment4.databinding.FragmentProductListBinding
import com.cs4520.assignment4.viewmodels.ProductListViewModel

class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        val productAdapter = ProductAdapter()

        binding.productList.adapter = productAdapter
        lifecycleScope.launchWhenCreated {
            viewModel.fetchProducts()
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                ProductListViewModel.State.SUCCESS -> {
                    Log.i("ProductListFragment", viewModel.products.value.toString())
                    productAdapter.submitList(viewModel.products.value)
                    binding.progressBar.visibility = View.GONE
                }

                ProductListViewModel.State.FAIL -> {
                    binding.progressBar.visibility = View.GONE
                }

                ProductListViewModel.State.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        return binding.root
    }

    private fun displayToast(@StringRes message: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
    }
}