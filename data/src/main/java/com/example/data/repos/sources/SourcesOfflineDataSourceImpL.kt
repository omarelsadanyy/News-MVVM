package com.example.data.repos.sources

import com.example.data.database.MyDataBase
import com.example.data.datasource.SourcesOfflineDataSource

import com.example.data.model.SourcesItemDto
import com.example.data.model.convertToDomain
import com.example.domain.model.SourcesItem



import javax.inject.Inject

class SourcesOfflineDataSourceImpL @Inject constructor(val myDataBase: MyDataBase):SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItemDto?>?) {
        myDataBase.sourcesDao().updateSources(sources)
    }

    override suspend fun getSources(category: String): List<SourcesItemDto> {
        return myDataBase.sourcesDao().getSourcesByCategoryId(category)

    }
}