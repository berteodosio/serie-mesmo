package com.berteodosio.seriemesmo.domain.useCase.show

import com.berteodosio.seriemesmo.data.show.repository.ShowRepository
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import com.berteodosio.seriemesmo.domain.useCase.base.BaseUseCase
import com.berteodosio.seriemesmo.domain.useCase.mapper.toShow
import com.berteodosio.seriemesmo.domain.useCase.model.Show
import io.reactivex.Observable

class FetchPopularShowsUseCase(private val showRepository: ShowRepository) : BaseUseCase() {

    fun execute(): Observable<Show> {
        return showRepository
            .fetchPopularShows()
            .map { it.toShow() }
    }

}


