package com.example.data.repos.news


import com.example.data.api.Services
import com.example.data.datasource.NewsOnlineDataSource
import com.example.data.model.ArticlesItemDto
import com.example.data.model.convertToDomain

import com.example.domain.model.ArticlesItem
import com.example.domain.model.NewsResponse

import javax.inject.Inject

class NewOnlineDataSourceImpL @Inject constructor(val webServices: Services): NewsOnlineDataSource {
    val apikey = "56430f55a4484780a1ad8fa4592490c1"
    override suspend fun getNewsSourceId(sourceId: String): List<ArticlesItemDto?>? {
        try {
            val result =  webServices.getNewsFromSources(apikey,sourceId)
            return result.articles
        } catch (ex:Exception){
            throw ex
        }

    }
}