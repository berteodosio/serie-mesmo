package com.berteodosio.seriemesmo.data.tmdb.model

import com.google.gson.annotations.SerializedName

// TODO: consider all fields nullable
data class TmdbShow(
    @SerializedName("id")
    var id: Long,

    @SerializedName("original_name")
    var name: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("vote_average")
    var voteAverage: Double
)