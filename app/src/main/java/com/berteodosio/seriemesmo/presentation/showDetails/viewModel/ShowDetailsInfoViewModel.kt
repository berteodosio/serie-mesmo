package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel

class ShowDetailsInfoViewModel(private val show: Show?) : BaseViewModel() {

    private val _viewState = MutableLiveData<ShowDetailsInfoViewState>()
    val viewState: LiveData<ShowDetailsInfoViewState>
        get() = _viewState

    init {
        displayShowDetailsInfo()
    }

    private fun displayShowDetailsInfo() {
        if (show == null) {
            _viewState.value = ShowDetailsInfoViewState.Error
            return
        }

        _viewState.value = ShowDetailsInfoViewState.Idle(show)
    }

}