package com.rizahanmiy.newsapp.data.entities

import com.google.gson.annotations.SerializedName

data class BaseDataSourceApi(
    @SerializedName("status")
    val status:String?,
    @SerializedName("sources")
    val sources: MutableList<NewsSourceApi>
)