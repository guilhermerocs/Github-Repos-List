package br.com.guilherme.githubreposlist.data.remote

import br.com.guilherme.githubreposlist.data.ApiGithub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Response

class RepositoriesRemote {

    fun fetchPublicRepositories(): Flow<Response?> {
        return flow {
            try {
                val repos = ApiGithub.apiService.fetchRepos()
                emit(repos)
            } catch (e: Exception) {
                emit(null)
            }
        }
    }
}