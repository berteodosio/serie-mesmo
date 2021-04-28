package com.berteodosio.seriemesmo.domain.usecase.show

import com.berteodosio.seriemesmo.data.show.repository.ShowRepository
import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import com.berteodosio.seriemesmo.domain.usecase.base.BaseUseCase
import com.berteodosio.seriemesmo.domain.usecase.model.Show
import io.reactivex.Observable

class FetchPopularShowsUseCase(private val showRepository: ShowRepository) : BaseUseCase() {

    fun execute(): Observable<Show> {
        return showRepository
            .fetchPopularShows()
            .map { it.toShow() }
    }

    // TODO: refactor
    private fun TmdbShow.toShow(): Show {
        return Show(id = this.id, name = this.name, overview = this.overview, voteAverage = this.voteAverage, posterUrl = "https://www.themoviedb.org/t/p/w1280${this.posterPath}")
    }

}


