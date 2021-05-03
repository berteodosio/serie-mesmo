package com.berteodosio.seriemesmo.presentation.seasonDetails.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.domain.model.Season
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.verification.VerificationMode
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class SeasonDetailsViewModelTest {

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fetchSeasonDetailsUseCase: FetchSeasonDetailsUseCase
    private lateinit var viewModel: SeasonDetailsViewModel

    @Before
    fun setup() {
        setupRxJavaSchedulers()

        fetchSeasonDetailsUseCase = mock()
        viewModel = SeasonDetailsViewModel(
            showId = SHOW_ID,
            seasonNumber = SEASON_NUMBER,
            seasonName = SEASON_NAME,
            fetchSeasonDetailsUseCase = fetchSeasonDetailsUseCase
        )
    }

    private fun setupRxJavaSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `Test Fetch Data Success`() {
        val mockedSeason: Season = mockSeason()
        `when`(fetchSeasonDetailsUseCase.execute(SHOW_ID, SEASON_NUMBER))
            .thenReturn(Single.just(mockedSeason))

        val viewStateObserver: Observer<SeasonDetailsViewState> = mock()
        viewModel.viewState.observeForever(viewStateObserver)

        viewModel.onInitialization()

        verify(viewStateObserver).onChanged(SeasonDetailsViewState.Loading(SEASON_NAME))
        verify(viewStateObserver).onChanged(SeasonDetailsViewState.DisplayingContent(mockedSeason))
    }

    private fun mockSeason(): Season = Season(
        SEASON_NAME, "https://test.png", 3L, emptyList(), 1L
    )

    private companion object {
        const val SHOW_ID = 60574L
        const val SEASON_NUMBER = 1L
        const val SEASON_NAME = "Test Season Name"
    }

}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}