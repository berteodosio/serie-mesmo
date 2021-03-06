package com.berteodosio.seriemesmo.presentation.showDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.berteodosio.seriemesmo.domain.useCase.base.setupCommonSchedulers
import com.berteodosio.seriemesmo.domain.useCase.show.FetchShowDetailsUseCase
import com.berteodosio.seriemesmo.presentation.base.viewModel.BaseViewModel
import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger

class ShowDetailsViewModel(
    private val showId: Long,
    private val fetchShowDetailsUseCase: FetchShowDetailsUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<ShowDetailsViewState>()
    val viewState: LiveData<ShowDetailsViewState>
        get() = _viewState

    init {
        fetchShowDetails()
    }

    private fun setLoadingState() {
        _viewState.value = ShowDetailsViewState.Loading
    }

    private fun fetchShowDetails() {
        setLoadingState()
        val disposable = fetchShowDetailsUseCase
            .execute(showId)
            .setupCommonSchedulers()
            .subscribe(
                { show -> _viewState.value = ShowDetailsViewState.ShowDetailsLoaded(show) },
                { e ->
                    AppLogger.e(TAG, "Error fetching Show Details for $showId", e)
                    _viewState.value = ShowDetailsViewState.Error(e)
                }
            )

        addDisposable(disposable)
    }

}