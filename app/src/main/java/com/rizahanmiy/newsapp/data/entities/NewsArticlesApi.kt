package com.rizahanmiy.newsapp.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsArticlesApi(
    @SerializedName("source")
    val source: SourceApi?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content")
    val content: String?,
): Serializable

