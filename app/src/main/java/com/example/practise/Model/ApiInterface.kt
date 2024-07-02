package com.example.practise.Model

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("everything?q=tesla&from=2024-06-02&sortBy=publishedAt&apiKey=11182f65f685432e81e0c8feb171dbd8")
    fun getNews() : Call<NewsData>

}