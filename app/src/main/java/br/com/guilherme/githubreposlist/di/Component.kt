package br.com.guilherme.githubreposlist.di

import br.com.guilherme.githubreposlist.ui.view.GitReposFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class, ViewModelModule::class])
interface Component {

    fun inject(gitReposFragment: GitReposFragment)
}