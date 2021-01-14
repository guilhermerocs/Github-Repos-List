package br.com.guilherme.githubreposlist.data.model.entity

import com.google.gson.annotations.SerializedName

data class Owner(

    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatar_url: String

)