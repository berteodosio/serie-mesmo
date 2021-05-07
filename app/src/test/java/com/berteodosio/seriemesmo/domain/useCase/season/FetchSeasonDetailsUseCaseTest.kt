package com.berteodosio.seriemesmo.domain.useCase.season

import com.berteodosio.seriemesmo.repositoryModule
import com.berteodosio.seriemesmo.tmdbModule
import com.berteodosio.seriemesmo.useCaseModule
import org.junit.Assert.assertEquals
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class FetchSeasonDetailsUseCaseTest {

    private val kodein = Kodein {
        import(tmdbModule)
        import(repositoryModule)
        import(useCaseModule)
    }

    @Test
    fun `GIVEN the Peaky Blinders season 1 showId WHEN I fetch season details THEN the correct information must be returned on the Season object`() {
        val useCase by kodein.instance<FetchSeasonDetailsUseCase>()
        val season = useCase.execute(60574L, 1).blockingGet()

        assertEquals("Series name is wrong", "Series 1", season.name)
        assertEquals("Episode Count is not OK", 6, season.episodes.size)
        assertEquals("Episode 5 contains the wrong title", "Episode 5", season.episodes[4].name)
        assertEquals(
            "Episode 4 overview contains the wrong value",
            "Thomas Shelby's war with the Lee family of gypsies escalates, and Campbell puts further pressure on him to deliver the stolen guns. Meanwhile Thomas's brother John plans to marry a former prostitute, but Thomas suspects that she's still on the game.",
            season.episodes[3].overview
        )
    }
}