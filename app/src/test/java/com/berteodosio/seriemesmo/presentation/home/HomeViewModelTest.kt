package com.berteodosio.seriemesmo.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.domain.useCase.show.FetchPopularShowsUseCase
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeNavigationEvent
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeViewModel
import com.berteodosio.seriemesmo.presentation.home.viewModel.HomeViewState
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.refEq
import org.mockito.kotlin.verify

class HomeViewModelTest {

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fetchPopularShowsUseCase: FetchPopularShowsUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        setupRxJavaSchedulers()
        fetchPopularShowsUseCase = mock()
        viewModel = HomeViewModel(fetchPopularShowsUseCase)
    }

    private fun setupRxJavaSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `Test Fetch Popular Shows success case`() {
        val showsList: List<Show> = listOf(mock(), mock())

        `when`(fetchPopularShowsUseCase.execute())
            .thenReturn(Observable.fromArray(*showsList.toTypedArray()))

        val observer: Observer<HomeViewState> = mock()
        viewModel.viewState.observeForever(observer)

        viewModel.onInitialization()

        val captor = argumentCaptor<HomeViewState>()
        verify(observer).onChanged(HomeViewState.Loading)
        verify(observer, Mockito.times(3)).onChanged(captor.capture())

        Assert.assertEquals(showsList, (captor.allValues.last() as HomeViewState.DisplayingShows).shows)
    }

    @Test
    fun `Test Fetch Popular Shows failure case`() {
        val exception = Exception("General Error")
        `when`(fetchPopularShowsUseCase.execute())
            .thenReturn(Observable.error(exception))

        val observer: Observer<HomeViewState> = mock()
        viewModel.viewState.observeForever(observer)

        viewModel.onInitialization()

        val captor = argumentCaptor<HomeViewState>()
        verify(observer).onChanged(HomeViewState.Initial)
        verify(observer).onChanged(HomeViewState.Loading)
        verify(observer).onChanged(ArgumentMatchers.refEq(HomeViewState.Error(exception)))
        verify(observer, Mockito.times(3)).onChanged(captor.capture())

        captor.allValues.forEach { println(it) }

        Assert.assertEquals(exception, (captor.allValues.last() as HomeViewState.Error).e)
    }

    @Test
    fun `Test if onShowClick dispatches the navigation event properly`() {
        val observer: Observer<HomeNavigationEvent> = mock()
        viewModel.navigationEvents.observeForever(observer)

        val show: Show = mock()
        viewModel.onShowClick(show)

        verify(observer).onChanged(refEq(HomeNavigationEvent.NavigateToShowDetails(show.id)))
    }
}