package com.berteodosio.seriemesmo

import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbDefaultRepository
import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.TmdbDefaultApi
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase
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
    bind<FetchSeasonDetailsUseCase>() with provider { FetchSeasonDetailsUseCase(instance()) }
}

val tmdbModule = Kodein.Module("TMDB Module") {
    bind<TmdbApi>() with singleton { TmdbDefaultApi() }
}

val repositoryModule = Kodein.Module("Repository Module") {
    bind<TmdbRepository>() with singleton { TmdbDefaultRepository(instance(), BuildConfig.TMDB_API_KEY) }
}