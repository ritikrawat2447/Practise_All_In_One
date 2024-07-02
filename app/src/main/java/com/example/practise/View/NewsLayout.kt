package com.example.practise.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practise.R
import com.example.practise.ViewModel.NewsViewModel

class NewsLayout : AppCompatActivity() {

    lateinit var myRecyclerView : RecyclerView
    lateinit var myAdapter: NewsAdapter
    private val newViewModel = NewsViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_layout)


        myRecyclerView = findViewById(R.id.recyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        newViewModel.newsLiveData.observe(this, Observer { articles ->
            myAdapter = NewsAdapter(articles, this)
            myRecyclerView.adapter = myAdapter

            myAdapter.setItemClickListener(object : NewsAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@NewsLayout, NewsContent::class.java)
                    intent.putExtra("author", articles[position].author)
                    intent.putExtra("title", articles[position].title)
                    intent.putExtra("description", articles[position].description)
                    intent.putExtra("image", articles[position].urlToImage)
                    startActivity(intent)
                }
            })
        })
        newViewModel.errorLiveData.observe(this , Observer { error ->
            Log.d("Retrofit Test", "Error message: $error")
        })

        newViewModel.fetchNews()


    }
}