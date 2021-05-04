package com.berteodosio.seriemesmo.domain.useCase.show

import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.domain.model.Show
import com.berteodosio.seriemesmo.repositoryModule
import com.berteodosio.seriemesmo.tmdbModule
import com.berteodosio.seriemesmo.useCaseModule
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FetchShowDetailsUseCaseTest {

    private val kodein = Kodein {
        import(tmdbModule)
        import(repositoryModule)
        import(useCaseModule)
    }

    @Test
    fun `Assert special season is discarded`() {
        val show = fetchPeakyBlindersShow()
        assertNull("Special season is being returned", show.seasons.find { it.name.equals("specials", ignoreCase = true) })
    }

    private fun fetchPeakyBlindersShow(): Show {
        val useCase: FetchShowDetailsUseCase by kodein.instance<FetchShowDetailsUseCase>()
        return useCase.execute(PEAKY_BLINDERS_SHOW_ID).blockingGet()
    }

    @Test
    fun `Assert general show info is correct`() {
        val show = fetchPeakyBlindersShow()
        assertEquals("Show Id is wrong", PEAKY_BLINDERS_SHOW_ID, show.id)
        assertEquals("Show name is wrong", "Peaky Blinders", show.name)
    }

    @Test
    fun `Test UseCase fails when repository fails`() {
        val testError = Exception()
        val repository: TmdbRepository = mock()
        val useCase = FetchShowDetailsUseCase(repository)
        val showId = any<Long>()
        whenever(repository.fetchShowDetails(showId)).thenReturn(Single.error(testError))

        useCase
            .execute(showId)
            .test()
            .assertError(testError)
    }

    private companion object {
        const val PEAKY_BLINDERS_SHOW_ID = 60574L

    }
}