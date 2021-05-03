package com.berteodosio.seriemesmo.data.tmdb.repository

import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbEpisode
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbSeason
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable
import io.reactivex.Single

class TmdbDefaultRepository(private val tmdbApi: TmdbApi) : TmdbRepository {

    override fun fetchPopularShows(): Observable<TmdbShow> {
        return tmdbApi
            .service()
            .fetchPopularShows()
            .flatMapObservable { Observable.fromArray(*it.results?.toTypedArray() ?: emptyArray()) }
    }

    override fun fetchShowDetails(showId: Long): Single<TmdbShow> {
        return tmdbApi
            .service()
            .fetchShowDetails(showId = showId)
    }

    override fun fetchSeasonDetails(showId: Long, seasonNumber: Long): Single<TmdbSeason> {
        return tmdbApi
            .service()
            .fetchSeasonDetails(showId = showId, seasonNumber = seasonNumber)
    }

}