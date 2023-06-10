package com.example.news_app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.ui.NewsFragment.Companion.articlesItem

class NewsContentFragment:Fragment() {

    lateinit var image_contnet: ImageView
    lateinit var author_content: TextView
    lateinit var title_content: TextView
    lateinit var date_content: TextView
    lateinit var descreption_content: TextView
    lateinit var view_full_article: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_newscontent,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }

    private fun initviews() {
       image_contnet = requireView().findViewById(R.id.image_content)
        author_content = requireView().findViewById(R.id.title_author_content)
        title_content = requireView().findViewById(R.id.title_news_content)
        date_content = requireView().findViewById(R.id.date_time_content)
        descreption_content = requireView().findViewById(R.id.description_content)
        view_full_article = requireView().findViewById(R.id.full_content_url)

        ///////////////
          Glide.with(requireContext())
              .load(articlesItem?.urlToImage)
              .into(image_contnet)
        author_content.text= articlesItem?.author
        title_content.text= articlesItem?.title
        date_content.text= articlesItem?.publishedAt
        descreption_content.text= articlesItem?.description
        view_full_article.setOnClickListener {
             val intent=Intent(Intent.ACTION_VIEW, Uri.parse(articlesItem?.url))
            startActivity(intent)
        }
    }
}