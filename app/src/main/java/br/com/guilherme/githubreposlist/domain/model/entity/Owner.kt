package br.com.guilherme.githubreposlist.domain.model.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(

    @Json(name ="login") val login: String?,
    @Json(name ="id") val id: Int?,
    @Json(name ="avatar_url") val avatar_url: String?

): Parcelable