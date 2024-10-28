package com.example.data.datasource

import com.example.data.model.SourcesItemDto


interface SourcesOfflineDataSource {
    suspend fun updateSources(sources:List<SourcesItemDto?>?)
    suspend fun getSources(category: String):List<SourcesItemDto?>?
}