package br.com.guilherme.githubreposlist.data


sealed class DataState<out T> {
    class Success<out T>(val result: T) : DataState<T>()
    object Empty : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    class Error(
        val throwable: Throwable,
        val errorCode: Int? = null,
        val errorMessage: String? = null
    ) : DataState<Nothing>()
}