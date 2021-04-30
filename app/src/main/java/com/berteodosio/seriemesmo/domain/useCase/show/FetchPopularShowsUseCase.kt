package com.berteodosio.seriemesmo.domain.useCase.show

import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.domain.useCase.base.BaseUseCase
import com.berteodosio.seriemesmo.domain.mapper.toShow
import com.berteodosio.seriemesmo.domain.model.Show
import io.reactivex.Observable

class FetchPopularShowsUseCase(private val tmdbRepository: TmdbRepository) : BaseUseCase() {

    fun execute(): Observable<Show> {
        return tmdbRepository
            .fetchPopularShows()
            .map { it.toShow() }
    }

}


