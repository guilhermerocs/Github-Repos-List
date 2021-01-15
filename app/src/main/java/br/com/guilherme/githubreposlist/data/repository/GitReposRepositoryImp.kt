package br.com.guilherme.githubreposlist.data.repository

import br.com.guilherme.githubreposlist.data.source.remote.GitRepositoriesRemote
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import kotlinx.coroutines.flow.Flow

class GitReposRepositoryImp : GitReposRepository {

    private val remote = GitRepositoriesRemote()

    override suspend fun fetchRepos(): Flow<List<GitRepository>?> {
        return remote.fetchPublicRepositories()
    }

}