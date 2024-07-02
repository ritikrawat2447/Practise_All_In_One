package com.example.practise.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practise.Model.ApiInterface
import com.example.practise.Model.Article
import com.example.practise.Model.NewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsViewModel : ViewModel() {
    val newsLiveData = MutableLiveData<List<Article>>()
    val errorLiveData = MutableLiveData<String>()
    private val apiInterface : ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun fetchNews(){
        val call = apiInterface.getNews()
        call.enqueue(object : Callback<NewsData?> {
            override fun onResponse(p0: Call<NewsData?>, p1: Response<NewsData?>) {
                if ( p1.isSuccessful ){
                    newsLiveData.postValue(p1.body()?.articles!!)
                }else{
                    errorLiveData.postValue("Error ${p1.code()}")
                }
            }

            override fun onFailure(p0: Call<NewsData?>, p1: Throwable) {
                errorLiveData.postValue("Error $p1")
            }
        })
    }
}