package com.berteodosio.seriemesmo.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Episode(
    val number: Long,
    val name: String,
    val overview: String,
    val airDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(number)
        parcel.writeString(name)
        parcel.writeString(overview)
        parcel.writeString(airDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Episode> {
        override fun createFromParcel(parcel: Parcel): Episode {
            return Episode(parcel)
        }

        override fun newArray(size: Int): Array<Episode?> {
            return arrayOfNulls(size)
        }
    }
}