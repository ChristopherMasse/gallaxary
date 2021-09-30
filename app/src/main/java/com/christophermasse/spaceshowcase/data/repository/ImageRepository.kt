package com.christophermasse.spaceshowcase.data.repository

import com.christophermasse.spaceshowcase.data.ImageWrapper
import com.christophermasse.spaceshowcase.data.api.AuthInterceptor
import com.christophermasse.spaceshowcase.data.api.ImageApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository(authInterceptor: AuthInterceptor): ImageRepo {

    private val client: OkHttpClient

    private val retrofit: Retrofit

    private val BASE_URL = "https://api.nasa.gov/planetary/"

    private val imageApi: ImageApi

    init{
        // Add a logging interceptor
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        //Create client with interceptor
        client = OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .build();

        // Build retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        //Make the api
        imageApi = retrofit.create(ImageApi::class.java)
    }

    override suspend fun getImagesBetweenDates(
        start: String,
        end: String
    ): Response<List<ImageWrapper>> {
        return imageApi.getImagesBetweenDates(start, end)
    }

    override suspend fun getImageAtDate(date: String): Response<ImageWrapper> {
        return imageApi.getSingleImage(date)
    }
}