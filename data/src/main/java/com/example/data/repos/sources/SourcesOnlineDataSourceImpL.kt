package com.example.data.repos.sources


import com.example.data.api.Services
import com.example.data.datasource.SourcesOnlineDataSource
import com.example.data.model.SourcesItemDto
import com.example.data.model.convertToDomain
import com.example.domain.model.SourcesResponse


import java.lang.Exception
import javax.inject.Inject

class SourcesOnlineDataSourceImpL @Inject constructor(val webServices: Services):SourcesOnlineDataSource {
    val apikey = "56430f55a4484780a1ad8fa4592490c1"
    override suspend fun getSources(category:String): List<SourcesItemDto?>? {
        try {
           val result = webServices.getNewssources(apikey,category)
            return result.sources
        }catch (ex:Exception){
            throw ex
        }
    }
}