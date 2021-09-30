package com.christophermasse.spaceshowcase.data.api

import com.christophermasse.spaceshowcase.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthInterceptor: Interceptor {

    init{
        Timber.d("Created an instance of AuthInterceptor")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val interceptUrl = original.url.newBuilder()
            .addQueryParameter("api_key", ApiKey.API_KEY)
            .build()
        val newReq = original.newBuilder().url(interceptUrl).build()
        return chain.proceed(newReq)
    }

}