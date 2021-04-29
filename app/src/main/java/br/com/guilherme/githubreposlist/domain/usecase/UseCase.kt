package br.com.guilherme.githubreposlist.domain.usecase

import br.com.guilherme.githubreposlist.data.DataState

interface UseCase<in Params, out Output> {
    suspend fun execute(params: Params? = null): DataState<Output>
}