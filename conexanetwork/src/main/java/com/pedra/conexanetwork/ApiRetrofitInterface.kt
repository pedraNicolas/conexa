package com.pedra.conexanetwork

import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.conexanetwork.dtos.users.UserDTO

interface ApiRetrofitInterface {

    suspend fun getNews(): List<NewsDTO>
    suspend fun getAllUsers(): List<UserDTO>
    suspend fun getUserById(id: String): UserDTO

}