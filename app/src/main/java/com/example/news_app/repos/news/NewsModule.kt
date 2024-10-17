package com.example.news_app.repos.news

import com.example.news_app.api.Services
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
    fun provideNewOnlineDataSource(webServices:Services):NewsOnlineDataSource{
        return NewOnlineDataSourceImpL(webServices)
    }


}