package com.pedra.conexarepositories.interfaces

import com.pedra.conexanetwork.dtos.news.NewsDTO
import java.lang.Exception

interface NewsRepositoryInterface {

    @Throws(Exception::class)
    suspend fun getAllNews(): List<NewsDTO>

}