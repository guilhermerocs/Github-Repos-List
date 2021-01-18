package br.com.guilherme.githubreposlist.di

import br.com.guilherme.githubreposlist.data.repository.GitReposRepositoryImp
import br.com.guilherme.githubreposlist.data.source.remote.GitRepositoriesRemote
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import dagger.Module
import dagger.Provides

@Module
class Module {

    @Provides
    fun providesGitReposRepository(): GitReposRepository {
        return GitReposRepositoryImp(providesGitReposRemote())
    }

    @Provides
    fun providesGitReposRemote(): GitRepositoriesRemote {
        return GitRepositoriesRemote()
    }

}