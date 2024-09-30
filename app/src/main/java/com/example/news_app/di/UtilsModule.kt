package com.example.news_app.di

import android.content.Context
import com.example.news_app.NetworkHandler
import com.example.news_app.NetworkHandlerImpL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context):NetworkHandler{
        return NetworkHandlerImpL(context)
    }
}