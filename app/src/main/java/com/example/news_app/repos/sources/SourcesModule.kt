package com.example.news_app.repos.sources

import com.example.news_app.NetworkHandler
import com.example.news_app.api.Services
import com.example.news_app.database.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun providerOnlineDataSource(webServices:Services):SourcesOnlineDataSource{
        return SourcesOnlineDataSourceImpL(webServices)
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