package com.example.data.repos.news


import com.example.data.api.Services

import com.example.domain.model.ArticlesItemDTO
import com.example.domain.model.NewsResponseDTO
import com.example.domain.repos.NewsOnlineDataSource
import com.example.news_app.model.convertToDTO
import javax.inject.Inject

class NewOnlineDataSourceImpL @Inject constructor(val webServices: Services): NewsOnlineDataSource {
    val apikey = "56430f55a4484780a1ad8fa4592490c1"
    override suspend fun getNewsSourceId(sourceId: String): List<ArticlesItemDTO?>? {
        try {
            val result =  webServices.getNewsFromSources(apikey,sourceId)
            return result.convertToDTO(NewsResponseDTO::class.java).articles
        } catch (ex:Exception){
            throw ex
        }

    }
}