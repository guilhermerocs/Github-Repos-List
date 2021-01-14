package br.com.guilherme.githubreposlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.guilherme.githubreposlist.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModelGit: GitRepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        observeViewModel()
    }


    private fun initViewModel() {
        viewModelGit = ViewModelProvider(this).get(GitRepositoriesViewModel::class.java)
        viewModelGit.fetchRepos()
    }

    private fun observeViewModel() {
        viewModelGit.apply {
            error.observe(this@MainActivity) {
                showError(it)
            }

            gitRepos.observe(this@MainActivity) {

            }
        }
    }


    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}