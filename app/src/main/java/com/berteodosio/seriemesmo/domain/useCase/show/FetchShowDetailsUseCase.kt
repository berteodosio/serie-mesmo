package com.berteodosio.seriemesmo.domain.useCase.show

import com.berteodosio.seriemesmo.data.show.repository.ShowRepository
import com.berteodosio.seriemesmo.domain.useCase.base.BaseUseCase
import com.berteodosio.seriemesmo.domain.useCase.mapper.toShow
import com.berteodosio.seriemesmo.domain.useCase.model.Show
import io.reactivex.Single

class FetchShowDetailsUseCase(private val showRepository: ShowRepository) : BaseUseCase() {

    fun execute(showId: Long): Single<Show> {
        return showRepository
            .fetchShowDetails(showId)
            .map { it.toShow() }
    }

}