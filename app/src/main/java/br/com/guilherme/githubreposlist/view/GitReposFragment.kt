package br.com.guilherme.githubreposlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.guilherme.githubreposlist.R

class GitReposFragment : Fragment(R.layout.fragment_git_repos) {

    lateinit var viewModelGit: GitRepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_git_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observeViewModel()

    }

    private fun initViewModel() {
        viewModelGit = ViewModelProvider(this).get(GitRepositoriesViewModel::class.java)
        viewModelGit.fetchRepos()
    }

    private fun observeViewModel() {
        viewModelGit.apply {
            error.observe(viewLifecycleOwner) {
                showError(it)
            }

            gitRepos.observe(viewLifecycleOwner) {

            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}