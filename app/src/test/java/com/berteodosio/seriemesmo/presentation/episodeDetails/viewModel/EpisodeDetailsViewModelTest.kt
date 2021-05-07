package com.berteodosio.seriemesmo.presentation.episodeDetails.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.berteodosio.seriemesmo.presentation.episodeDetails.formatter.DateFormatter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class EpisodeDetailsViewModelTest {

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EpisodeDetailsViewModel
    private lateinit var observer: Observer<EpisodeDetailsViewState>

    @Before
    fun setup() {
        val dateFormatter: DateFormatter = createDateFormatter()

        viewModel = EpisodeDetailsViewModel(
            episodeName = EPISODE_NAME,
            episodeOverview = EPISODE_OVERVIEW,
            episodeCoverUrl = EPISODE_COVER_URL,
            episodeAirDate = EPISODE_AIR_DATE,
            episodeNumber = EPISODE_NUMBER,
            dateFormatter = dateFormatter
        )

        observer = mock()
        viewModel.viewState.observeForever(observer)
    }

    private fun createDateFormatter(): DateFormatter {
        val dateFormatter: DateFormatter = mock()

        // we force the return of the method to avoid it returning null
        whenever(dateFormatter.format(any())).thenReturn(EPISODE_AIR_DATE)
        return dateFormatter
    }

    @Test
    fun `Assert DisplayingContent state is the initial state`() {
        verify(observer).onChanged(
            EpisodeDetailsViewState.DisplayingContent(
                EPISODE_NAME,
                EPISODE_OVERVIEW,
                EPISODE_COVER_URL,
                EPISODE_AIR_DATE,
                EPISODE_NUMBER
            )
        )
    }

    private companion object {
        const val EPISODE_NAME = "Episode"
        const val EPISODE_OVERVIEW = "Overview"
        const val EPISODE_COVER_URL = "https://test.png"
        const val EPISODE_AIR_DATE = "2020-03-12"
        const val EPISODE_NUMBER = 3L
    }

}