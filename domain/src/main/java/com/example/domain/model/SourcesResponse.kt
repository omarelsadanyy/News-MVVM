package com.example.domain.model


data class SourcesResponse(


    val sources: List<SourcesItem?>? = null,


    val status: String? = null,


    val code: String? = null,


    val message: String? = null

    )

data class SourcesItem(


    val country: String? = null,


    val name: String? = null,


    val description: String? = null,


    val language: String? = null,


    val id: String,


    val category: String? = null,


    val url: String? = null
)
