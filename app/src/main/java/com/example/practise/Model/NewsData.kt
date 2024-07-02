package com.example.practise.Model

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)