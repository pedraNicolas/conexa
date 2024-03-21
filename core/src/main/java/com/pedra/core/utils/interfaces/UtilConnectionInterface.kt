package com.pedra.core.utils.interfaces

import android.content.Context

interface UtilConnectionInterface {

    fun getConnectionType(): String?

    fun checkInternetConnection(): Boolean
}