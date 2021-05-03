package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berteodosio.seriemesmo.domain.model.Show

class ShowDetailsSeasonsViewModelFactory(private val show: Show?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowDetailsSeasonsViewModel(show) as T
    }
}