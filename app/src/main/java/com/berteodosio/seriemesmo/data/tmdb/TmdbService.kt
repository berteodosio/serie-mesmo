package com.berteodosio.seriemesmo.data.tmdb

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbSeason
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("popular")
    fun fetchPopularShows(): Single<FetchPopularShowsResponse>

    @GET("{showId}")
    fun fetchShowDetails(@Path(value = "showId") showId: Long): Single<TmdbShow>

    @GET("{showId}/season/{seasonNumber}")
    fun fetchSeasonDetails(@Path(value = "showId") showId: Long, @Path("seasonNumber") seasonNumber: Long): Single<TmdbSeason>

    data class FetchPopularShowsResponse(var results: List<TmdbShow>?)

}