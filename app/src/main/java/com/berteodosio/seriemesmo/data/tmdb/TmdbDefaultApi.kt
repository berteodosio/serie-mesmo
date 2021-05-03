package com.berteodosio.seriemesmo.data.tmdb

import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TmdbDefaultApi : TmdbApi {

    private fun retrofit(converter: Converter.Factory) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converter)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    override fun service(): TmdbService = retrofit(GsonConverterFactory.create()).create(TmdbService::class.java)

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/tv/"
    }

}