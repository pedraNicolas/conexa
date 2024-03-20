package com.pedra.conexanetwork.dtos.news

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
