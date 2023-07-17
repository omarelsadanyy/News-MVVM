package com.example.news_app.ui.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.databinding.FragmentnewscontentBinding
import com.example.news_app.ui.news.NewsFragment.Companion.articlesItem

class NewsContentFragment:Fragment() {
    lateinit var viewBinding:FragmentnewscontentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragmentnewscontent,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }

    private fun initviews() {
          Glide.with(requireContext())
              .load(articlesItem?.urlToImage)
              .into(viewBinding.imageContent)
       viewBinding.titleAuthorContent.text= articlesItem?.author
        viewBinding.titleNewsContent.text= articlesItem?.title
        viewBinding.dateTimeContent.text= articlesItem?.publishedAt
        viewBinding.descriptionContent.text= articlesItem?.description
        viewBinding.fullContentUrl.setOnClickListener {
             val intent=Intent(Intent.ACTION_VIEW, Uri.parse(articlesItem?.url))
            startActivity(intent)
        }
    }
}