package com.berteodosio.seriemesmo.domain.useCase.model

import android.os.Parcel
import android.os.Parcelable

// TODO: take a look at parcelable nullable
data class Show(
    val id: Long,
    val name: String,
    val overview: String,
    val posterUrl: String,
    val voteAverage: Double,
    val backdropUrl: String,
    val genres: List<String>,
    val status: String,
    val originalLanguage: String,
    val seasons: List<Season>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Season) ?: emptyList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(overview)
        parcel.writeString(posterUrl)
        parcel.writeDouble(voteAverage)
        parcel.writeString(backdropUrl)
        parcel.writeStringList(genres)
        parcel.writeString(status)
        parcel.writeString(originalLanguage)
        parcel.writeTypedList(seasons)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Show> {
        override fun createFromParcel(parcel: Parcel): Show {
            return Show(parcel)
        }

        override fun newArray(size: Int): Array<Show?> {
            return arrayOfNulls(size)
        }
    }
}

