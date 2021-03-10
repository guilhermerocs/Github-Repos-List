package br.com.guilherme.githubreposlist.domain.model.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepository(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "full_name") val full_name: String?,
    @Json(name = "owner") val owner: Owner?,
    @Json(name = "description") val description: String?,
) : Parcelable