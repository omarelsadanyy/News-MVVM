package com.example.news_app.repos

import androidx.room.PrimaryKey
import com.example.news_app.NetworkHandler
import com.example.news_app.api.ApiManager
import com.example.news_app.database.MyDataBase
import com.example.news_app.repos.sources.SourcesOfflineDataSource
import com.example.news_app.repos.sources.SourcesOfflineDataSourceImpL
import com.example.news_app.repos.sources.SourcesOnlineDataSource
import com.example.news_app.repos.sources.SourcesOnlineDataSourceImpL
import com.example.news_app.repos.sources.SourcesRepository
import com.example.news_app.repos.sources.SourcesRepositoryImpL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(Singleton::class)
object RepositoriesModule {

    @Provides
    fun providerOnlineDataSource():SourcesOnlineDataSource{
        return SourcesOnlineDataSourceImpL(ApiManager.getApis())
    }

    @Provides
    fun provideOfflineDataSources(dataBase: MyDataBase):SourcesOfflineDataSource{
        return SourcesOfflineDataSourceImpL(dataBase)
    }

    @Singleton
    @Provides
    fun provideDataBase():MyDataBase{
        return  MyDataBase.getInstance()
    }

    @Provides
    fun provideSourcesRepo(onlineDataSource: SourcesOnlineDataSource
    ,offlineDataSource: SourcesOfflineDataSource ,
                           networkHandler: NetworkHandler):SourcesRepository{
        return SourcesRepositoryImpL(onlineDataSource,
            offlineDataSource,networkHandler)
    }


}