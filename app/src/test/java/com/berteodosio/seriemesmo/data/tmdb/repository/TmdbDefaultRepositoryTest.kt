package com.berteodosio.seriemesmo.data.tmdb.repository

import com.berteodosio.seriemesmo.data.tmdb.model.TmdbShow
import io.reactivex.Single
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TmdbDefaultRepositoryTest {

    @Test
    fun `Test fetch show details success case`() {
        val repository = createRepository()

        val tmdbShow: TmdbShow = mock()
        whenever(repository.fetchShowDetails(any()))
            .thenReturn(Single.just(tmdbShow))

        val testObserver = repository.fetchShowDetails(any()).test()
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(tmdbShow)
    }

    @Test
    fun `Test fetch show details failure case`() {
        val exception = Exception("General error")
        val repository = createRepository()
        whenever(repository.fetchShowDetails(any()))
            .thenReturn(Single.error(exception))

        val testObserver = repository.fetchShowDetails(any()).test()
        testObserver
            .assertError(exception)
    }

    private fun createRepository(): TmdbDefaultRepository {
        return TmdbDefaultRepository(mock())
    }

}