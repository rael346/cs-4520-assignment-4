package com.cs4520.assignment4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.adapters.ProductAdapter
import com.cs4520.assignment4.api.productsDataset
import com.cs4520.assignment4.databinding.FragmentLoginBinding
import com.cs4520.assignment4.databinding.FragmentProductListBinding
import com.cs4520.assignment4.model.Product
import com.cs4520.assignment4.model.categorize

class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        val productAdapter = ProductAdapter()

        binding.productList.adapter = productAdapter
        val productList = productsDataset.map { categorize(it[0] as String, it[1] as String, it[2] as String?, it[3] as Int) }
        productAdapter.submitList(productList as MutableList<Product>)

        return binding.root
    }
}