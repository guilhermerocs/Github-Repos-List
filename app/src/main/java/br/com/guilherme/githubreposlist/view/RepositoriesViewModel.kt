package br.com.guilherme.githubreposlist.view


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.guilherme.githubreposlist.data.remote.RepositoriesRemote
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RepositoriesViewModel : ViewModel() {

    private val repositoriesRemote = RepositoriesRemote()

    fun fetchRepos() {
        viewModelScope.launch(IO) {
            repositoriesRemote.fetchPublicRepositories().collect {
                if (it != null) {

                }
            }
        }
    }

}