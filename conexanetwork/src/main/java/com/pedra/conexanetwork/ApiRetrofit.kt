package com.pedra.conexanetwork

import com.pedra.conexanetwork.dtos.news.NewsDTO
import com.pedra.conexanetwork.dtos.users.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRetrofit {

    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserDTO>>

    @GET("/users/{id}")
    suspend fun getUserByID(@Path("id") id: String): Response<UserDTO>

    @GET("/posts")
    suspend fun getAllNews(): Response<List<NewsDTO>>
}