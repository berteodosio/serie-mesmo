package com.berteodosio.seriemesmo.data.tmdb.repository

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbSeason
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable
import io.reactivex.Single

interface TmdbRepository {

    fun fetchPopularShows(): Observable<TmdbShow>

    fun fetchShowDetails(showId: Long): Single<TmdbShow>

    fun fetchSeasonDetails(showId: Long, seasonNumber: Long): Single<TmdbSeason>

}