package com.pedra.conexanetwork

import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.conexanetwork.dtos.users.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiRetrofit {

    @GET("/users")
    suspend fun getAllUsers(): Response<UserDTO>

    @GET("/posts")
    suspend fun getAllNews(): Response<List<NewsDTO>>
}