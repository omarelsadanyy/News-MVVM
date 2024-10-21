package com.example.data.repos.news

import com.example.domain.model.ArticlesItemDTO
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpL @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItemDTO?>? {

        try {
            val result =    newsOnlineDataSource.getNewsSourceId(sourceId)
            return result
        }catch (ex:Exception){
            throw ex
        }
    }
}