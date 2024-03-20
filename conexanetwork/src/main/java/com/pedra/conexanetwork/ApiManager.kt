package com.pedra.conexanetwork

import android.accounts.NetworkErrorException
import com.pedra.core.ConstantsNetwork
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager {

    companion object {
        private lateinit var instance: ApiManager
        private lateinit var apiRetrofit: ApiRetrofit

        fun getInstance(
            url: String,
        ): ApiManager {
            val retrofit = getRetrofitConfig(
                url
            )
            apiRetrofit = retrofit.create(ApiRetrofit::class.java)
            instance = ApiManager()
            return instance
        }

        @Throws(NetworkErrorException::class)
        private fun getRetrofitConfig(baseUrl: String): Retrofit {
            val connectTimeout = ConstantsNetwork.DEFAULT_TIMEOUT
            val readTimeout = ConstantsNetwork.DEFAULT_TIMEOUT


            /** HTTP CLIENT */
            val builder = OkHttpClient.Builder()
                .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
                .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            val client = builder.build()

            /** RETROFIT */
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }

    fun getApiRetrofit():ApiRetrofit{
        return apiRetrofit
    }

}