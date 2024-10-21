package com.example.data.repos.sources


import com.example.data.api.Services

import com.example.domain.model.SourcesItemDTO
import com.example.domain.model.SourcesResponseDTO
import com.example.domain.repos.SourcesOnlineDataSource
import com.example.news_app.model.convertToDTO
import java.lang.Exception
import javax.inject.Inject

class SourcesOnlineDataSourceImpL @Inject constructor(val webServices: Services):SourcesOnlineDataSource {
    val apikey = "56430f55a4484780a1ad8fa4592490c1"
    override suspend fun getSources(category:String): List<SourcesItemDTO?>? {
        try {
           val result = webServices.getNewssources(apikey,category)
            return result.convertToDTO(SourcesResponseDTO::class.java).sources
        }catch (ex:Exception){
            throw ex
        }
    }
}