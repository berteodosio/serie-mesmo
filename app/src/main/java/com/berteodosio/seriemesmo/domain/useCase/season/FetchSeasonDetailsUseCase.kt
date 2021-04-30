package com.berteodosio.seriemesmo.domain.useCase.season

import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.domain.mapper.toSeason
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.domain.useCase.base.BaseUseCase
import io.reactivex.Single

class FetchSeasonDetailsUseCase(private val tmdbRepository: TmdbRepository) : BaseUseCase() {

    fun execute(showId: Long, seasonNumber: Long): Single<Season> {
        return tmdbRepository
            .fetchSeasonDetails(showId, seasonNumber)
            .map { it.toSeason() }
    }

}