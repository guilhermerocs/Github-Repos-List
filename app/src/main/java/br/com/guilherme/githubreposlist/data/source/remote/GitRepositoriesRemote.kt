package br.com.guilherme.githubreposlist.data.source.remote

import br.com.guilherme.githubreposlist.data.ApiGithubInterface
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitRepositoriesRemote constructor(
    private val apiService: ApiGithubInterface
) {

    fun fetchPublicRepositories(): Flow<List<GitRepository>?> {
        return flow {
            try {
                val repos = apiService.fetchRepos()
                emit(repos)
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }
}