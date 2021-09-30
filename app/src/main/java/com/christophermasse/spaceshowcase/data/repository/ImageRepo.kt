package com.christophermasse.spaceshowcase.data.repository

import com.christophermasse.spaceshowcase.data.ImageWrapper
import retrofit2.Response

interface ImageRepo {

    suspend fun getImagesBetweenDates(start: String, end: String): Response<List<ImageWrapper>>

    suspend fun getImageAtDate(date: String): Response<ImageWrapper>
}