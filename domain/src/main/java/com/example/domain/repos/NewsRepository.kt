package com.example.domain.repos


import com.example.domain.model.ArticlesItem

interface NewsRepository {
    suspend fun  getNews(sourceId:String):List<ArticlesItem?>?
}

