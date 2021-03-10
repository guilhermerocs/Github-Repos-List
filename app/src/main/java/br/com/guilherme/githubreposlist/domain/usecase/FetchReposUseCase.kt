package br.com.guilherme.githubreposlist.domain.usecase

import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import kotlinx.coroutines.flow.Flow


class FetchReposUseCase constructor(
    private val repository: GitReposRepository
) : FetchReposUseCaseI {

    override suspend fun execute(): Flow<List<GitRepository>?> {
        return repository.fetchRepos()
    }

}