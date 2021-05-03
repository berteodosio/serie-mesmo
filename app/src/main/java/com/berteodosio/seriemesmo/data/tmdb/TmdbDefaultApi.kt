package com.berteodosio.seriemesmo.data.tmdb

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TmdbDefaultApi(private val tmdbApiKeyInterceptor: TmdbApiKeyInterceptor) : TmdbApi {

    private fun retrofit(converter: Converter.Factory) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .apply { addApiKeyInterceptor() }
        .build()

    override fun service(): TmdbService = retrofit(GsonConverterFactory.create()).create(TmdbService::class.java)

    private fun Retrofit.Builder.addApiKeyInterceptor() {
        client(OkHttpClient().newBuilder().addInterceptor(tmdbApiKeyInterceptor).build())
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/tv/"
    }

}