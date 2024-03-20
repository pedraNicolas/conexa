package com.pedra.conexarepositories.usecase

import com.pedra.conexarepositories.NewsRepository

class NewsUseCase {
    private val newsRepository = NewsRepository()

    suspend fun getNews(){
        newsRepository.getNews()
    }
}