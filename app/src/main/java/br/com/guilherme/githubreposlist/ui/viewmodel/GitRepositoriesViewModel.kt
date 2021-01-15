package br.com.guilherme.githubreposlist.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.guilherme.githubreposlist.data.model.entity.GitRepository
import br.com.guilherme.githubreposlist.data.remote.GitRepositoriesRemote
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class GitRepositoriesViewModel : ViewModel() {

    private val repositoriesRemote = GitRepositoriesRemote()

    val gitRepos = MutableLiveData<List<GitRepository>>()
    val error = MutableLiveData<String>()

    fun fetchRepos() {
        viewModelScope.launch(IO) {
            repositoriesRemote.fetchPublicRepositories().collect {
                if (it != null)
                    gitRepos.postValue(it)
                else
                    error.postValue("Ocorreu um erro inesperado, tente novamente mais tarde")
            }
        }
    }

}