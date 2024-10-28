package com.example.domain.repos

import com.example.domain.model.SourcesItem


interface SourcesRepository {
    suspend fun  getSources(category:String):List<SourcesItem?>?
}


