package com.example.data.repos.news

import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository
import com.example.data.api.Services
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
   fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource):NewsRepository{
        return NewsRepositoryImpL(newsOnlineDataSource)
   }

    @Provides
    fun provideNewOnlineDataSource(webServices: Services):NewsOnlineDataSource{
        return NewOnlineDataSourceImpL(webServices)
    }


}