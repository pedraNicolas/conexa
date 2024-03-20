package com.pedra.conexarepositories.mappers

import com.pedra.conexamodel.NewsUI
import com.pedra.conexanetwork.dtos.news.NewsDTO
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface NewsMapper {

    companion object {
        val INSTANCE: NewsMapper = Mappers.getMapper(NewsMapper::class.java)
    }

    fun newsToUI(newsDTO: NewsDTO): NewsUI
}