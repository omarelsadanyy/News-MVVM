package com.example.news_app.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news_app.model.SourcesItem


@Database(entities = [SourcesItem::class], version = 1, exportSchema = false)
abstract class MyDataBase:RoomDatabase() {
abstract fun sourcesDao():SourcesDao

 companion object{
     var dataBase:MyDataBase?=null
     const val DATABASE_NAME="NewsDataBase"
     fun init(context:Context){
         if(dataBase==null){
             dataBase= Room.databaseBuilder(
                 context,MyDataBase::class.java,DATABASE_NAME)
                 .fallbackToDestructiveMigration()
                 .build()
         }

     }
     fun getInstance():MyDataBase{
         return dataBase!!
     }
 }
}