package com.example.news_app.repos.news

import com.example.news_app.ui.news.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object AdaptersModule {

    @Provides
    fun provideNewsAdapter():NewsAdapter{
        return NewsAdapter()
    }
}