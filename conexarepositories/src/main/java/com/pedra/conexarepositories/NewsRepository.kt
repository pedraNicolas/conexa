package com.pedra.conexarepositories

import com.pedra.conexanetwork.ApiRetrofitInterface
import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.conexarepositories.interfaces.NewsRepositoryInterface
import java.lang.Exception

internal class NewsRepository(
    private val apiRetrofitImpl: ApiRetrofitInterface
) : NewsRepositoryInterface {

    @Throws(Exception::class)
    override suspend fun getAllNews(): List<NewsDTO> {
        return apiRetrofitImpl.getNews()
    }
}