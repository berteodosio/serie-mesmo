package com.berteodosio.seriemesmo.data.show.repository

import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable

class ShowDefaultRepository(private val tmdbApi: TmdbApi) : ShowRepository {

    override fun fetchPopularShows(): Observable<TmdbShow> {
        return tmdbApi
            .service()
            .fetchPopularShows("")
            .flatMapObservable { Observable.fromArray(*it.results.toTypedArray()) }
    }

}