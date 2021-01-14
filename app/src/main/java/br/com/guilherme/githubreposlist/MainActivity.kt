package br.com.guilherme.githubreposlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.guilherme.githubreposlist.view.RepositoriesViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)

        viewModel.fetchRepos()
    }
}