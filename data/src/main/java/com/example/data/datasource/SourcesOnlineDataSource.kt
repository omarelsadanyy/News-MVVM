package com.example.data.datasource

import com.example.data.model.SourcesItemDto


interface SourcesOnlineDataSource {
    suspend fun  getSources(category:String):List<SourcesItemDto?>?
}