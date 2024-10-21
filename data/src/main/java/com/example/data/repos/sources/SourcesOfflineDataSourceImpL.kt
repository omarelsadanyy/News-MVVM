package com.example.data.repos.sources

import com.example.data.database.MyDataBase
import com.example.data.model.SourcesItem

import com.example.domain.model.SourcesItemDTO
import com.example.domain.repos.SourcesOfflineDataSource
import com.example.news_app.model.convertToDTO
import javax.inject.Inject

class SourcesOfflineDataSourceImpL @Inject constructor(val myDataBase: MyDataBase):SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItemDTO?>?) {
        myDataBase.sourcesDao().updateSources(sources?.map { it?.convertToDTO(SourcesItem::class.java) })
    }

    override suspend fun getSources(category: String): List<SourcesItemDTO> {
        return myDataBase.sourcesDao().getSourcesByCategoryId(category)
            .map { it.convertToDTO(SourcesItemDTO::class.java) }
    }
}