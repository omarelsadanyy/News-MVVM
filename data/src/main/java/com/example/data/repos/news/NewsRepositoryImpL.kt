package com.example.data.repos.news

import com.example.data.datasource.NewsOnlineDataSource
import com.example.data.model.NewsResponseDto
import com.example.data.model.convertToDomain
import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse

import com.example.domain.repos.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpL @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {

        try {
            val result = newsOnlineDataSource.getNewsSourceId(sourceId)
            return result?.convertToDomain(NewsResponse::class.java)?.articles
        }catch (ex:Exception){
            throw ex
        }
    }
}