package com.berteodosio.seriemesmo.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Season(
    val name: String,
    val posterUrl: String,
    val episodeCount: Long,
    val episodes: List<Episode>,
    val number: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.createTypedArrayList(Episode) ?: emptyList(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(posterUrl)
        parcel.writeLong(episodeCount)
        parcel.writeTypedList(episodes)
        parcel.writeLong(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Season> {
        override fun createFromParcel(parcel: Parcel): Season {
            return Season(parcel)
        }

        override fun newArray(size: Int): Array<Season?> {
            return arrayOfNulls(size)
        }
    }

}