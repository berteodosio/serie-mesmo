package com.berteodosio.seriemesmo.data.show.repository

import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable
import io.reactivex.Single

class ShowDefaultRepository(
    private val tmdbApi: TmdbApi,
    private val tmdbApiKey: String
) : ShowRepository {

    override fun fetchPopularShows(): Observable<TmdbShow> {
        return tmdbApi
            .service()
            .fetchPopularShows(tmdbApiKey)
            .flatMapObservable { Observable.fromArray(*it.results?.toTypedArray() ?: emptyArray()) }
    }

    override fun fetchShowDetails(showId: Long): Single<TmdbShow> {
        return tmdbApi
            .service()
            .fetchShowDetails(apiKey = tmdbApiKey, showId = showId)
    }

}