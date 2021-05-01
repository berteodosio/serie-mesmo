package com.berteodosio.seriemesmo.data.tmdb.model

import com.google.gson.annotations.SerializedName

data class TmdbEpisode(
    @SerializedName("episode_number")
    var number: Long?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("air_date")
    var airDate: String?,

    @SerializedName("still_path")
    var stillPath: String?
)