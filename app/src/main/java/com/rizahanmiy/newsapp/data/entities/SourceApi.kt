package com.rizahanmiy.newsapp.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SourceApi(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) : Serializable

