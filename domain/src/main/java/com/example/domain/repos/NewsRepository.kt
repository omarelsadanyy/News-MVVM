package com.example.domain.repos


import com.example.domain.model.ArticlesItemDTO

interface NewsRepository {
    suspend fun  getNews(sourceId:String):List<ArticlesItemDTO?>?
}

interface  NewsOnlineDataSource{
    suspend fun  getNewsSourceId(sourceId:String):List<ArticlesItemDTO?>?
}