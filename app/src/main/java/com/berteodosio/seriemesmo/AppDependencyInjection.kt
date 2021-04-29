package com.berteodosio.seriemesmo

import com.berteodosio.seriemesmo.data.show.repository.ShowDefaultRepository
import com.berteodosio.seriemesmo.data.show.repository.ShowRepository
import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.TmdbDefaultApi
import com.berteodosio.seriemesmo.domain.useCase.show.FetchPopularShowsUseCase
import com.berteodosio.seriemesmo.domain.useCase.show.FetchShowDetailsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val useCaseModule = Kodein.Module("Use Cases Module") {
    bind<FetchPopularShowsUseCase>() with provider { FetchPopularShowsUseCase(instance()) }
    bind<FetchShowDetailsUseCase>() with provider { FetchShowDetailsUseCase(instance()) }
}

val tmdbModule = Kodein.Module("TMDB Module") {
    bind<TmdbApi>() with singleton { TmdbDefaultApi() }
}

val repositoryModule = Kodein.Module("Repository Module") {
    bind<ShowRepository>() with singleton { ShowDefaultRepository(instance(), BuildConfig.TMDB_API_KEY) }
}