package com.pedra.conexarepositories

import com.pedra.conexanetwork.ApiRetrofitImpl

internal class NewsRepository {

    private val apiRetrofitImpl = ApiRetrofitImpl()

    suspend fun getNews(){
        apiRetrofitImpl.getNews()
    }
}