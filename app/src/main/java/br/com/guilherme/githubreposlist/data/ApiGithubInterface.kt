package br.com.guilherme.githubreposlist.data

import okhttp3.ResponseBody
import retrofit2.http.GET


interface ApiGithubInterface {


    @GET("repositories")
    suspend fun fetchRepos(): ResponseBody?

}