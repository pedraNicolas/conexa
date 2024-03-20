package com.pedra.conexamodel

data class NewsUI (
    val id: Int,
    val thumbnail: String,
    val image: String,
    val category: String,
    val title: String,
    val content: String,
    val publishedAt: String,
    val updatedAt: String,
    val userId: Int
)