package com.example.data.repos.sources

import com.example.domain.repos.SourcesOfflineDataSource
import com.example.domain.repos.SourcesOnlineDataSource
import com.example.domain.repos.SourcesRepository

import com.example.data.api.Services
import com.example.domain.utils.NetworkHandler
import com.example.data.database.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun providerOnlineDataSource(webServices: Services): SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpL(webServices)
    }

    @Provides
    fun provideOfflineDataSources(dataBase: MyDataBase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpL(dataBase)
    }


    @Provides
    fun provideSourcesRepo(
        onlineDataSource: SourcesOnlineDataSource
    ,offlineDataSource: SourcesOfflineDataSource ,
                           networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpL(onlineDataSource,
            offlineDataSource,networkHandler)
    }


}