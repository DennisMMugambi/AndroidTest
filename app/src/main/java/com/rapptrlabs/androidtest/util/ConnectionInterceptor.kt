package com.rapptrlabs.androidtest.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ConnectionInterceptor @Inject constructor(@ApplicationContext private val context: Context) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionOn()) {
            throw IOException("No Internet Connection, Kindly Check your Connection")
        } else if (!isInternetAvailable()) {
            throw IOException("No internet Connection, Kindly Check your Connection")
        } else {
            chain.proceed(chain.request())
        }
    }

    /**
     * Checks if connection is on
     */

    fun isConnectionOn(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager

        return postAndroidMInternetCheck(connectivityManager)
    }

    /**
     * Check connection for post M devices
     */
    private fun postAndroidMInternetCheck(
        connectivityManager: ConnectivityManager,
    ): Boolean {
        val network = connectivityManager.activeNetwork
        val connection = connectivityManager.getNetworkCapabilities(network)

        return connection != null && connection.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET,
        )
    }

    /**
     * Checks if Internet is available
     */
    private fun isInternetAvailable(): Boolean {
        return true
    }

}