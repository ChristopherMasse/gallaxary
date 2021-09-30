package com.christophermasse.spaceshowcase.data.api

import com.christophermasse.spaceshowcase.data.ImageWrapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ImageApi {

    companion object{
        const val path = "apod"
    }
    @GET(path)

    suspend fun getSingleImage(@Query(value = "date") date:String):  Response<ImageWrapper>

    @GET(path)
    fun getMultipleImages(@Query(value = "start_date") startDate: String,
                          @Query(value = "end_date") endDate: String): Call<List<ImageWrapper>>

    @GET(path)
    suspend fun getImagesBetweenDates(@Query(value = "start_date") startDate: String,
                                      @Query(value = "end_date") endDate: String): Response<List<ImageWrapper>>

}