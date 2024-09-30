package com.example.news_app.repos.news

import com.example.news_app.Constants
import com.example.news_app.api.Services
import com.example.news_app.model.ArticlesItem

class NewOnlineDataSourceImpL(val webServices:Services): NewsOnlineDataSource {
    override suspend fun getNewsSourceId(sourceId: String): List<ArticlesItem?>? {
        try {
            val result =  webServices.getNewsFromSources(Constants.apikey,sourceId)
            return result.articles
        } catch (ex:Exception){
            throw ex
        }

    }
}