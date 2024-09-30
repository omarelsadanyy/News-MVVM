package com.example.news_app.repos.sources

import com.example.news_app.database.MyDataBase
import com.example.news_app.model.SourcesItem

class SourcesOfflineDataSourceImpL(val myDataBase: MyDataBase):SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDataBase.sourcesDao().updateSources(sources)
    }

    override suspend fun getSources(category: String): List<SourcesItem> {
        return myDataBase.sourcesDao().getSourcesByCategoryId(category)
    }
}