package br.com.guilherme.githubreposlist.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch
import javax.inject.Inject


class GitRepositoriesViewModel @Inject constructor(
    private val gitReposUseCase: FetchReposUseCase
) : ViewModel() {


    val gitRepos = MutableLiveData<List<GitRepository>>()
    var error = MutableLiveData<String>()
    var loading = MutableLiveData(false)
    private val coroutinesContext = IO

    fun fetchRepos() {
        loading.postValue(true)
        viewModelScope.launch(coroutinesContext) {
            gitReposUseCase.execute()
                .catch { e ->
                    error.postValue(e.message)
                    loading.postValue(false)
                }
                .collect { repos ->
                    gitRepos.postValue(repos)
                    loading.postValue(false)
                }
        }
    }
}