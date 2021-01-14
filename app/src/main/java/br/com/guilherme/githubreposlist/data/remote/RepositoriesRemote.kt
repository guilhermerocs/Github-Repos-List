package br.com.guilherme.githubreposlist.data.remote

import br.com.guilherme.githubreposlist.data.ApiGithub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody

class RepositoriesRemote {

    fun fetchPublicRepositories(): Flow<ResponseBody?> {
        return flow {
            try {
                val repos = ApiGithub.apiService.fetchRepos()
                emit(repos)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(null)
            }
        }
    }
}