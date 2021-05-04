package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger

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
            AppLogger.i(TAG, "Null show has been passed as an argument. Unable to display information")
            return
        }

        _viewState.value = ShowDetailsInfoViewState.DisplayingContent(show)
    }

}