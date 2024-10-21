package com.example.data.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object MyDataBaseModule {
        @Singleton
        @Provides
        fun provideDataBase(@ApplicationContext applicationContext : Context): MyDataBase {
              MyDataBase.init(applicationContext)
                return MyDataBase.getInstance()
        }
}