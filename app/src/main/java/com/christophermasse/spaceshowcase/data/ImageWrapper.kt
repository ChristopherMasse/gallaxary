package com.christophermasse.spaceshowcase.data

import com.google.gson.annotations.SerializedName

data class ImageWrapper(
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("mediaType")
    val mediaType: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)