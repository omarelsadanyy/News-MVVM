package com.example.domain.model

data class NewsResponse(
//DTO : Data Transfer Object
    val totalResults: Int? = null,

       val articles: List<ArticlesItem?>? = null,


    ): BaseRespone()

data class ArticlesItem(

    val publishedAt: String? = null,

    val author: String? = null,

    val urlToImage: String? = null,

    val description: String? = null,

    val source: SourcesItem? = null,

    val title: String? = null,

    val url: String? = null,

    val content: String? = null
)


//data class Source(
//
//    @field:SerializedName("name")
//    val name: String? = null,
//
//    @field:SerializedName("id")
//    val id: String? = null
//)


