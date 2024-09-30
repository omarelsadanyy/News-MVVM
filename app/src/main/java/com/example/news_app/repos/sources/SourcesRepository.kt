package com.example.news_app.repos.sources

import com.example.news_app.model.SourcesItem

interface SourcesRepository {
    suspend fun  getSources(category:String):List<SourcesItem?>?
}

interface SourcesOnlineDataSource{
    suspend fun  getSources(category:String):List<SourcesItem?>?
}

interface SourcesOfflineDataSource{
    suspend fun updateSources(sources:List<SourcesItem?>?)
    suspend fun getSources(category: String):List<SourcesItem?>?

}