package com.berteodosio.seriemesmo.data.tmdb.repository

import com.berteodosio.seriemesmo.data.tmdb.TmdbService
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbSeason
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable
import io.reactivex.Single

class TmdbDefaultRepository(private val tmdbService: TmdbService) : TmdbRepository {

    override fun fetchPopularShows(): Observable<TmdbShow> {
        return tmdbService
            .fetchPopularShows()
            .flatMapObservable { Observable.fromArray(*it.results?.toTypedArray() ?: emptyArray()) }
    }

    override fun fetchShowDetails(showId: Long): Single<TmdbShow> {
        return tmdbService
            .fetchShowDetails(showId = showId)
    }

    override fun fetchSeasonDetails(showId: Long, seasonNumber: Long): Single<TmdbSeason> {
        return tmdbService
            .fetchSeasonDetails(showId = showId, seasonNumber = seasonNumber)
    }

}