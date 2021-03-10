package br.com.guilherme.githubreposlist.application

import android.app.Application
import br.com.guilherme.githubreposlist.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationGit : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationGit)
            modules(module)
        }
    }
}