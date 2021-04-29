package com.berteodosio.seriemesmo.data.show.repository

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable
import io.reactivex.Single

interface ShowRepository {

    fun fetchPopularShows(): Observable<TmdbShow>

    fun fetchShowDetails(showId: Long): Single<TmdbShow>

}