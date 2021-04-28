package com.berteodosio.seriemesmo

import com.berteodosio.seriemesmo.data.show.repository.ShowDefaultRepository
import com.berteodosio.seriemesmo.data.show.repository.ShowRepository
import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.TmdbDefaultApi
import com.berteodosio.seriemesmo.domain.usecase.show.FetchPopularShowsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val useCaseModule = Kodein.Module("Use Cases Module") {
    bind<FetchPopularShowsUseCase>() with provider { FetchPopularShowsUseCase(instance()) }
}

val tmdbModule = Kodein.Module("TMDB Module") {
    bind<TmdbApi>() with provider { TmdbDefaultApi() }
}

val repositoryModule = Kodein.Module("Repository Module") {
    bind<ShowRepository>() with provider { ShowDefaultRepository(instance()) }
}