package com.berteodosio.seriemesmo.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berteodosio.seriemesmo.domain.useCase.show.FetchPopularShowsUseCase

class HomeViewModelFactory(
    private val fetchPopularShowsUseCase: FetchPopularShowsUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(fetchPopularShowsUseCase) as T
    }

}