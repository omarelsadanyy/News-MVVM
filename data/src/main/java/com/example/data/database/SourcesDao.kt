package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.data.model.SourcesItem

@Dao
interface SourcesDao {
    @Query( "select * from SourcesItem")
    suspend fun getSources():List<SourcesItem?>

    @Query( "select * from SourcesItem where category=:category")
    suspend fun getSourcesByCategoryId(category:String):List<SourcesItem>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sourcesItem: List<SourcesItem?>?)
}