package com.pedra.conexarepositories.interfaces

import com.pedra.conexanetwork.dtos.news.NewsDTO

interface NewsRepositoryInterface {
    suspend fun getAllNews(): List<NewsDTO>

}