package com.berteodosio.seriemesmo.data.tmdb.model

import com.google.gson.annotations.SerializedName

// TODO: consider all fields nullable
data class TmdbShow(
    @SerializedName("id")
    var id: Long?,

    @SerializedName("original_name")
    var name: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("vote_average")
    var voteAverage: Double?,

    @SerializedName("backdrop_path")
    var backdropPath: String?,

    @SerializedName("genres")
    var genres: List<TmdbGenre>?,

    @SerializedName("status")
    var status: String?,

    @SerializedName("original_language")
    var originalLanguage: String?,

    @SerializedName("seasons")
    var seasons: List<TmdbSeason>?
)