package com.example.practise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsLayout : AppCompatActivity() {

    lateinit var myRecyclerView : RecyclerView
    lateinit var myAdapter: NewsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_layout)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getNews()

        retrofitData.enqueue(object : Callback<NewsData?> {

            override fun onResponse(p0: Call<NewsData?>, p1: Response<NewsData?>) {
                Log.d("Retrofit Test"," Success message")
                val responseDataList = p1.body()?.articles!!

                myAdapter = NewsAdapter(responseDataList, this@NewsLayout)
                myAdapter.setItemClickListener(object : NewsAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@NewsLayout,NewsContent::class.java)
                        intent.putExtra("author",responseDataList[position].author)
                        intent.putExtra("title",responseDataList[position].title)
                        intent.putExtra("description",responseDataList[position].description)
                        intent.putExtra("image",responseDataList[position].urlToImage)
                        startActivity(intent)
                    }

                })

                myRecyclerView = findViewById(R.id.recyclerView)
                myRecyclerView.layoutManager = LinearLayoutManager(this@NewsLayout)
                myRecyclerView.adapter = myAdapter

            }

            override fun onFailure(p0: Call<NewsData?>, p1: Throwable) {
                Log.d("Retrofit Test","message " + p1.message)
            }
        })

    }
}