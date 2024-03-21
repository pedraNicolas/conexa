package com.pedra.conexarepositories.usecase

import com.pedra.conexamodel.NewsUI
import com.pedra.conexarepositories.interfaces.NewsRepositoryInterface
import com.pedra.conexarepositories.mappers.NewsMapper
import java.lang.Exception

class NewsUseCase(
    private val newsRepository: NewsRepositoryInterface
) {

    @Throws(Exception::class)
    suspend fun getNewsUIList(): List<NewsUI> {
        val mapper = NewsMapper.INSTANCE
        val list = newsRepository.getAllNews().map {
            mapper.newsToUI(it)
        }
        return list
    }
}