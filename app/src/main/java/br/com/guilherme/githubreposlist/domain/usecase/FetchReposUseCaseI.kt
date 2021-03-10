package br.com.guilherme.githubreposlist.domain.usecase

import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow

interface FetchReposUseCaseI {

    suspend fun execute(): Flow<List<GitRepository>?>
}