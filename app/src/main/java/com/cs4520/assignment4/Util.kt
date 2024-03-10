package com.cs4520.assignment4

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object Util {
    fun isInternetAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) ?: false
        }
    }
}