package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase

class SeasonDetailsViewModelFactory(
    private val showId: Long,
    private val seasonNumber: Long,
    private val seasonName: String,
    private val fetchSeasonDetailsUseCase: FetchSeasonDetailsUseCase
) : ViewModelProvider.Factory {

    // TODO: improve, avoid using reflection
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(Long::class.java, Long::class.java, String::class.java, FetchSeasonDetailsUseCase::class.java)
            .newInstance(showId, seasonNumber, seasonName, fetchSeasonDetailsUseCase)
    }
}