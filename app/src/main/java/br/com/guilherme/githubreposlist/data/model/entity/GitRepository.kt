package br.com.guilherme.githubreposlist.data.model.entity

import com.google.gson.annotations.SerializedName

data class GitRepository (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("description") val description: String,
)