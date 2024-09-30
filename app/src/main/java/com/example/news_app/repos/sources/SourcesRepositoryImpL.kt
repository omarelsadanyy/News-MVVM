package com.example.news_app.repos.sources

import com.example.news_app.NetworkHandler
import com.example.news_app.model.SourcesItem
import java.lang.Exception

class SourcesRepositoryImpL(val sourcesOnlineDataSource: SourcesOnlineDataSource,
                            val sourcesOfflineDataSource: SourcesOfflineDataSource,
                            val networkHandler: NetworkHandler):SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
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