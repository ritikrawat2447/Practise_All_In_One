package com.example.practise

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)