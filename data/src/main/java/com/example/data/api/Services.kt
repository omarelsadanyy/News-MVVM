package com.example.data.api





import com.example.data.model.SourcesResponseDto
import com.example.data.model.NewsResponseDto

import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("v2/top-headlines/sources")
    suspend fun getNewssources(
        @Query("apiKey") apikey:String,
        @Query("category") category:String
    ): SourcesResponseDto // what is the error? SourcesResponse is not found


    @GET("v2/everything")
    suspend fun getNewsFromSources(
        @Query ("apiKey") apikey: String?,
        @Query ("sources")source:String,
        @Query("q") query: String?=null
    ) : NewsResponseDto
}