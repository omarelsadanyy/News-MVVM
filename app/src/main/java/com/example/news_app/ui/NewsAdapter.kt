package com.example.news_app.ui

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.model.ArticlesItem
import java.util.Date

class NewsAdapter(var items:List<ArticlesItem?>?= null):Adapter<NewsAdapter.viewholder>() {
    class viewholder(itemview: View):ViewHolder(itemview){

        val author:TextView=itemview.findViewById(R.id.title_author)
        val Image:ImageView=itemview.findViewById(R.id.image_news)
        val date:TextView=itemview.findViewById(R.id.date_time)
        val title:TextView=itemview.findViewById(R.id.title_news)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       val view =LayoutInflater.from(parent.context)
           .inflate(R.layout.item_news,parent,false)
        return  viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       val item =items!![position]

        holder.title.setText(item?.title)
        holder.author.setText(item?.author)
        holder.date.setText(item?.publishedAt)
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.Image)


            onItemClickListener.let {
                holder.itemView.setOnClickListener {
                    onItemClickListener?.onClick(position,item!!)
                }
            }

    }

    override fun getItemCount(): Int =items?.size ?:0

    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onClick(position: Int,items: ArticlesItem)
    }
    fun changeData(data: List<ArticlesItem?>?){
        this.items=data

         notifyDataSetChanged()
    }
}