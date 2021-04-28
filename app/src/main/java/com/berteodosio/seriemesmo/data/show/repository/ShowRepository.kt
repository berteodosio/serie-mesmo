package com.berteodosio.seriemesmo.data.show.repository

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Observable

interface ShowRepository {

    fun fetchPopularShows(): Observable<TmdbShow>

}