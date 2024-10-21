package com.example.data.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
const val BaseUrl="https://newsapi.org/"


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logging=HttpLoggingInterceptor(
            object :HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    Log.e("api",message)
                }

            }
            )
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return logging
}

    @Provides
    @Singleton
    fun gsonConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(logging:HttpLoggingInterceptor):OkHttpClient{
        val client=OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideWebServices(retrofit: Retrofit): Services {
        return retrofit.create(Services::class.java)
    }
}