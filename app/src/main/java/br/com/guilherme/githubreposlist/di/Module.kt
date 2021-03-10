package br.com.guilherme.githubreposlist.di

import br.com.guilherme.githubreposlist.data.repository.GitReposRepositoryImp
import br.com.guilherme.githubreposlist.data.source.remote.GitRepositoriesRemote
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCase
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCaseI
import br.com.guilherme.githubreposlist.presentation.viewmodel.GitRepositoriesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val module = module {


    single { GitRepositoriesRemote() }

    single<GitReposRepository> { GitReposRepositoryImp(get()) }

    single<FetchReposUseCaseI> { FetchReposUseCase(get()) }

    viewModel { GitRepositoriesViewModel(Dispatchers.IO, get()) }

}