package com.example.news_app.repos.sources

import com.example.news_app.Constants
import com.example.news_app.api.Services
import com.example.news_app.model.SourcesItem
import com.example.news_app.ui.categories.Category
import java.lang.Exception

class SourcesOnlineDataSourceImpL(val webServices:Services):SourcesOnlineDataSource {
    override suspend fun getSources(category:String): List<SourcesItem?>? {
        try {
           val result = webServices.getNewssources(Constants.apikey,category)
            return result.sources
        }catch (ex:Exception){
            throw ex
        }
    }
}