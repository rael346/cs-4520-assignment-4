package com.cs4520.assignment4.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment4.R

class LoginViewModel: ViewModel() {
    enum class LoginResult {
        INITIAL, SUCCESS, FAIL
    }

    val error: MutableLiveData<Int?> = MutableLiveData<Int?>(null)
    val result: MutableLiveData<LoginResult> = MutableLiveData<LoginResult>(LoginResult.INITIAL)

    fun login(username: String, password: String) {
        if (username == "admin" && password == "admin") {
            result.value = LoginResult.SUCCESS
            error.value = null
        } else {
            result.value = LoginResult.FAIL
            error.value = R.string.login_fail_message
        }
    }

    fun reset() {
        result.value = LoginResult.INITIAL
        error.value = null
    }
}