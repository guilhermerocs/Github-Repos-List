package br.com.guilherme.githubreposlist.di

import br.com.guilherme.githubreposlist.data.repository.GitReposRepositoryImp
import br.com.guilherme.githubreposlist.data.source.remote.GitRepositoriesRemote
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCase
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCaseI
import dagger.Module
import dagger.Provides

@Module
class Module {

    @Provides
    fun providesFetchReposUseCase(): FetchReposUseCaseI {
        return FetchReposUseCase(providesGitReposRepository())
    }

    @Provides
    fun providesGitReposRepository(): GitReposRepository {
        return GitReposRepositoryImp(providesGitReposRemote())
    }

    @Provides
    fun providesGitReposRemote(): GitRepositoriesRemote {
        return GitRepositoriesRemote()
    }

}