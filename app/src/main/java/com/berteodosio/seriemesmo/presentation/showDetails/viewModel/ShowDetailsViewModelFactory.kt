package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berteodosio.seriemesmo.domain.useCase.show.FetchShowDetailsUseCase

class ShowDetailsViewModelFactory(
    private val showId: Long,
    private val fetchShowDetailsUseCase: FetchShowDetailsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowDetailsViewModel(showId, fetchShowDetailsUseCase) as T
    }
}