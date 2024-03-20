package com.pedra.conexanetwork

import com.pedra.conexanetwork.dtos.news.NewsDTO

interface ApiRetrofitInterface {

    suspend fun getNews(): List<NewsDTO>

}