package com.berteodosio.seriemesmo.data.tmdb.model

import com.google.gson.annotations.SerializedName

data class TmdbSeason(
    @SerializedName("name")
    var name: String?,

    @SerializedName("poster_path")
    var posterPath: String?,

    @SerializedName("episode_count")
    var episodeCount: Long?
)