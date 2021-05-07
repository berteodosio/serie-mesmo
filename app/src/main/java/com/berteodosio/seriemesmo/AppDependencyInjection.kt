package com.berteodosio.seriemesmo

import android.content.Context
import android.text.format.DateFormat
import com.berteodosio.seriemesmo.data.tmdb.TmdbApi
import com.berteodosio.seriemesmo.data.tmdb.TmdbApiKeyInterceptor
import com.berteodosio.seriemesmo.data.tmdb.TmdbDefaultApi
import com.berteodosio.seriemesmo.data.tmdb.TmdbService
import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbDefaultRepository
import com.berteodosio.seriemesmo.data.tmdb.repository.TmdbRepository
import com.berteodosio.seriemesmo.domain.useCase.season.FetchSeasonDetailsUseCase
import com.berteodosio.seriemesmo.domain.useCase.show.FetchPopularShowsUseCase
import com.berteodosio.seriemesmo.domain.useCase.show.FetchShowDetailsUseCase
import com.berteodosio.seriemesmo.presentation.episodeDetails.formatter.DateFormatter
import com.berteodosio.seriemesmo.presentation.episodeDetails.formatter.DefaultDateFormatter
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
    bind<TmdbApiKeyInterceptor>() with singleton { TmdbApiKeyInterceptor(BuildConfig.TMDB_API_KEY) }
    bind<TmdbApi>() with singleton { TmdbDefaultApi(instance()) }
    bind<TmdbService>() with singleton { instance<TmdbApi>().service() }
}

val repositoryModule = Kodein.Module("Repository Module") {
    bind<TmdbRepository>() with singleton { TmdbDefaultRepository(instance()) }
}

val generalModule = Kodein.Module("General Module") {
    bind<DateFormatter>() with singleton { DefaultDateFormatter(DateFormat.getDateFormat(instance(tag = "applicationContext"))) }
}

fun androidModule(context: Context) = Kodein.Module("Android Module") {
    bind<Context>(tag = "applicationContext") with provider { context }
}