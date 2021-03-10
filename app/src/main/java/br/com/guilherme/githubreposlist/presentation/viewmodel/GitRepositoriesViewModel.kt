package br.com.guilherme.githubreposlist.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCaseI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch


class GitRepositoriesViewModel constructor(
    private val coroutinesContext: CoroutineDispatcher,
    private val gitReposUseCase: FetchReposUseCaseI
) : ViewModel() {


    val gitRepos = MutableLiveData<List<GitRepository>>()
    var error = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()

    fun fetchRepos() {
        loading.postValue(true)
        viewModelScope.launch(coroutinesContext) {
            gitReposUseCase.execute()
                .catch { e ->
                    loading.postValue(false)
                    error.postValue(e.message)
                }
                .collect { repos ->
                    loading.postValue(false)
                    gitRepos.postValue(repos)
                }
        }
    }
}