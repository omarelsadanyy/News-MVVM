package com.example.news_app.ui.news

//provide news adapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AdaptersModule {

    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }

}