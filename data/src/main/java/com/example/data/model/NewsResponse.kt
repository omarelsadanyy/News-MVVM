package com.example.data.model



import com.example.news_app.model.BaseResponeDto
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

fun <T> Any.convertToDomain(clazz: Class<T>):T{ //
    val jsonString= Gson().toJson(this)
    return Gson().fromJson(jsonString,clazz)
}
data class NewsResponseDto(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItemDto?>? = null,


    ): BaseResponeDto()

data class ArticlesItemDto(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: SourcesItemDto? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
)





