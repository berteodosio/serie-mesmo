package com.berteodosio.seriemesmo.data.tmdb

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TmdbApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val requestBuilder: Request.Builder = original
            .newBuilder()
            .url(url)

        return chain.proceed(requestBuilder.build())
    }
}