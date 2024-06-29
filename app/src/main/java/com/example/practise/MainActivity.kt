package com.example.practise

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.practise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.newsButton.setOnClickListener{
            val intent = Intent(this@MainActivity,NewsLayout::class.java)
            startActivity(intent)
        }
        setContentView(mainBinding.root)
    }
}