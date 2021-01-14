package br.com.guilherme.githubreposlist.data.remote

import br.com.guilherme.githubreposlist.data.ApiGithub
import br.com.guilherme.githubreposlist.data.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitRepositoriesRemote {

    fun fetchPublicRepositories(): Flow<List<GitRepository>?> {
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