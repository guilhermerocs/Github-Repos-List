package br.com.guilherme.githubreposlist.data

import br.com.guilherme.githubreposlist.data.model.entity.GitRepository
import retrofit2.http.GET

interface ApiGithubInterface {

    @GET("repositories")
    suspend fun fetchRepos(): List<GitRepository>?

}