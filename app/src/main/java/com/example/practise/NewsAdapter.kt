package com.example.practise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practise.databinding.NewsRecyclerviewBinding
import com.squareup.picasso.Picasso

class NewsAdapter(var articles: List<Article>, context: Context ) : RecyclerView.Adapter<NewsAdapter.myViewHolder>()  {

    lateinit var mylistener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setItemClickListener(listener: onItemClickListener){
        mylistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_recyclerview,parent,false)
        return myViewHolder(itemView,mylistener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = articles[position]
        holder.author.text = currentItem.author
        holder.title.text = currentItem.title
        Picasso.get().load(currentItem.urlToImage).into(holder.image)
    }

    inner class myViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.image)
        val title = itemView.findViewById<TextView>(R.id.title1)
        val author = itemView.findViewById<TextView>(R.id.author)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}