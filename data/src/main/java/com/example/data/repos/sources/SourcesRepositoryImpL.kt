package com.example.data.repos.sources

import com.example.domain.model.SourcesItemDTO



import com.example.domain.repos.SourcesOfflineDataSource
import com.example.domain.repos.SourcesOnlineDataSource
import com.example.domain.repos.SourcesRepository
import com.example.domain.utils.NetworkHandler

import java.lang.Exception
import javax.inject.Inject

class SourcesRepositoryImpL  @Inject constructor (val sourcesOnlineDataSource: SourcesOnlineDataSource,
                            val sourcesOfflineDataSource: SourcesOfflineDataSource,
                            val networkHandler: NetworkHandler
):SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {
        try {
            if(networkHandler.isOnline()){
                val result= sourcesOnlineDataSource.getSources(category)
                sourcesOfflineDataSource.updateSources(result)
                return result
            }
            val res= sourcesOfflineDataSource.getSources(category)
            return res

        }catch (ex:Exception){
            return sourcesOfflineDataSource.getSources(category)
        }
    }
}