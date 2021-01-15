package br.com.guilherme.githubreposlist.domain.usecase

import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchReposUseCase @Inject constructor(
    private val repository: GitReposRepository
) {

    suspend fun execute(): Flow<List<GitRepository>?> {
        return repository.fetchRepos()
    }

}