package com.berteodosio.seriemesmo.domain.useCase.show

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.domain.useCase.base.BaseUseCase
import com.berteodosio.seriemesmo.domain.mapper.toShow
import com.berteodosio.seriemesmo.domain.model.Show
import io.reactivex.Single

class FetchShowDetailsUseCase(private val tmdbRepository: TmdbRepository) : BaseUseCase() {

    fun execute(showId: Long): Single<Show> {
        return tmdbRepository
            .fetchShowDetails(showId)
            .map(::removeSpecialSeason)
            .map { it.toShow() }
    }

    private fun removeSpecialSeason(show: TmdbShow): TmdbShow {
        return show.apply { seasons = seasons?.filter { it.name.equals("specials", ignoreCase = true).not() } }
    }

}