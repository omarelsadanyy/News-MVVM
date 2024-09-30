package com.example.news_app.repos.news

import com.example.news_app.model.ArticlesItem

class NewsRepositoryImpL(val newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {

        try {
            val result =    newsOnlineDataSource.getNewsSourceId(sourceId)
            return result
        }catch (ex:Exception){
            throw ex
        }
    }
}