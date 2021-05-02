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

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeasonDetailsViewModel(showId, seasonNumber, seasonName, fetchSeasonDetailsUseCase) as T
    }
}