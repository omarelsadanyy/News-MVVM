package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.SourcesItemDto


@Dao
interface SourcesDao {
    @Query( "select * from SourcesItemDto")
    suspend fun getSources():List<SourcesItemDto?>

    @Query( "select * from SourcesItemDto where category=:category")
    suspend fun getSourcesByCategoryId(category:String):List<SourcesItemDto>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sourcesItem: List<SourcesItemDto?>?)
}