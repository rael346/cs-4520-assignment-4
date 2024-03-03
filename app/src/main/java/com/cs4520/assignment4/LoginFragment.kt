package com.cs4520.assignment4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment4.databinding.FragmentLoginBinding
import com.cs4520.assignment4.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        loginButton.isEnabled = true

        viewModel.result.observe(viewLifecycleOwner) { result ->
            if (result == LoginViewModel.LoginResult.SUCCESS) {
                clearInputs()
                findNavController().navigate(R.id.productListFragment)
                displayToast(R.string.login_success_message)
            }

            if (result == LoginViewModel.LoginResult.FAIL) {
                clearInputs()
            }
        }

        loginButton.setOnClickListener {
            viewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString(),
            )
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                displayToast(error)
            }
        }

        return binding.root
    }

    private fun displayToast(@StringRes message: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
    }

    private fun clearInputs() {
        binding.username.text.clear()
        binding.password.text.clear()
    }
}