package com.example.news_app.model



import com.example.data.model.SourcesItem
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

fun <T> Any.convertToDTO(clazz: Class<T>):T{
    val jsonString= Gson().toJson(this)
    return Gson().fromJson(jsonString,clazz)
}
data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,


    ): BaseRespone()

data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: SourcesItem? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
)





