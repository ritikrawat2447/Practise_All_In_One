package com.example.practise.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practise.databinding.ActivityNewsContentBinding
import com.squareup.picasso.Picasso

class NewsContent : AppCompatActivity() {

    lateinit var contentBinding: ActivityNewsContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        contentBinding = ActivityNewsContentBinding.inflate(layoutInflater)

        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val description = intent.getStringExtra("description")
        val image = intent.getStringExtra("image")

        contentBinding.apply {
            newsTitle.text = title
            newsAuthor.text = author
            newsDescription.text = description
            Picasso.get().load(image).into(newsImage)
        }


        setContentView(contentBinding.root)



    }
}