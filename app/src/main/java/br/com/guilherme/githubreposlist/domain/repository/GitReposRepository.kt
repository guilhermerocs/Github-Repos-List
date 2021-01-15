package br.com.guilherme.githubreposlist.domain.repository

import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import kotlinx.coroutines.flow.Flow

interface GitReposRepository {
    suspend fun fetchRepos(): Flow<List<GitRepository>?>
}