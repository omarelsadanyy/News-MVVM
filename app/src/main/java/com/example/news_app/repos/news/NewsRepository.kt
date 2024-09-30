package com.example.news_app.repos.news

import com.example.news_app.model.ArticlesItem
import com.example.news_app.model.SourcesItem

interface NewsRepository {
    suspend fun  getNews(sourceId:String):List<ArticlesItem?>?
}

interface  NewsOnlineDataSource{
    suspend fun  getNewsSourceId(sourceId:String):List<ArticlesItem?>?
}