package com.httpurlcon.androidnetworking105kot.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnected {

    companion object {
        fun isNetworkConnected(context: Context): Boolean{
            var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE ) as ConnectivityManager
            val activateNetworkInfo = connectionManager.activeNetworkInfo

            return activateNetworkInfo != null && activateNetworkInfo.isConnectedOrConnecting
        }
    }
}