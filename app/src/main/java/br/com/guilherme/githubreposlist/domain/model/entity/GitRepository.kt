package br.com.guilherme.githubreposlist.domain.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitRepository(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("description") val description: String,
) : Parcelable