package com.berteodosio.seriemesmo.domain.useCase.show

import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.domain.useCase.base.BaseUseCase
import com.berteodosio.seriemesmo.domain.mapper.toShow
import com.berteodosio.seriemesmo.domain.model.Show
import io.reactivex.Single

class FetchShowDetailsUseCase(private val tmdbRepository: TmdbRepository) : BaseUseCase() {

    fun execute(showId: Long): Single<Show> {
        return tmdbRepository
            .fetchShowDetails(showId)
            .map { it.toShow() }
    }

}