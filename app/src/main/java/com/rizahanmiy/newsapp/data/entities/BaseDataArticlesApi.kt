package com.rizahanmiy.newsapp.data.entities

import com.google.gson.annotations.SerializedName

data class BaseDataArticlesApi(
    @SerializedName("status")
    val status:String?,
    @SerializedName("totalResults")
    val totalResults:Int?,
    @SerializedName("articles")
    val articles: MutableList<NewsArticlesApi>
)

