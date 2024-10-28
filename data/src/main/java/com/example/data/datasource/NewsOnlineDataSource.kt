package com.example.data.datasource

import com.example.data.model.ArticlesItemDto


interface NewsOnlineDataSource {

        suspend fun  getNewsSourceId(sourceId:String):List<ArticlesItemDto?>?

}