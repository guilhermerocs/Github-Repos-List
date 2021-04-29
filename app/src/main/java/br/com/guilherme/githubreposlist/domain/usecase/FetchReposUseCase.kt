package br.com.guilherme.githubreposlist.domain.usecase

import br.com.guilherme.githubreposlist.data.DataState
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


class FetchReposUseCase constructor(
    private val repository: GitReposRepository
) : FetchReposUseCaseI {

    override suspend fun execute(): Flow<DataState<List<GitRepository>?>> {
        return flow {
            repository.fetchRepos()
                .catch { e ->
                    emit(DataState.Error(e))
                }
                .collect {
                    emit(DataState.Success(it))
                }
        }
    }
}