package com.pedra.conexanetwork

import com.pedra.core.ConstantsNetwork
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ConstantsNetwork.JSON_PLACE_HOLDER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getNews(): ApiRetrofit {
       return getRetrofit().create(ApiRetrofit::class.java)
    }

//    companion object {
//        private lateinit var instance: ApiManager
//        private lateinit var apiRetrofit: ApiRetrofit
//
//        fun getInstance(
//            url: String,
//        ): ApiManager {
//            val retrofit = getRetrofitConfig(
//                url
//            )
//            apiRetrofit = retrofit.create(ApiRetrofit::class.java)
//            instance = ApiManager()
//            return instance
//        }
//
//        @Throws(NetworkErrorException::class)
//        private fun getRetrofitConfig(baseUrl: String): Retrofit {
//            val connectTimeout = ConstantsNetwork.DEFAULT_TIMEOUT
//            val readTimeout = ConstantsNetwork.DEFAULT_TIMEOUT
//
//
//            /** HTTP CLIENT */
//            val builder = OkHttpClient.Builder()
//                .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
//                .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
//            val client = builder.build()
//
//            /** RETROFIT */
//            return Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//    }
//
//    fun getApiRetrofit():ApiRetrofit{
//        return apiRetrofit
//    }

}