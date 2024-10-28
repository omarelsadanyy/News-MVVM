package com.example.data.repos.sources

import com.example.data.datasource.SourcesOfflineDataSource
import com.example.data.datasource.SourcesOnlineDataSource
import com.example.data.model.convertToDomain
import com.example.domain.model.SourcesItem
import com.example.domain.model.SourcesResponse
import com.example.domain.repos.SourcesRepository
import com.example.domain.utils.NetworkHandler

import java.lang.Exception
import javax.inject.Inject

class SourcesRepositoryImpL  @Inject constructor (val sourcesOnlineDataSource: SourcesOnlineDataSource,
                            val sourcesOfflineDataSource: SourcesOfflineDataSource,
                            val networkHandler: NetworkHandler
):SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            if(networkHandler.isOnline()){
                val result= sourcesOnlineDataSource.getSources(category)
                sourcesOfflineDataSource.updateSources(result)
                return result?.convertToDomain(SourcesResponse::class.java)?.sources
            }
            val res= sourcesOfflineDataSource.getSources(category)
            return res?.convertToDomain(SourcesResponse::class.java)?.sources

        }catch (ex:Exception){
            val result =sourcesOfflineDataSource.getSources(category)
            return result?.convertToDomain(SourcesResponse::class.java)?.sources
        }
    }
}