package br.com.guilherme.githubreposlist.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guilherme.githubreposlist.R
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.databinding.FragmentGitReposBinding
import br.com.guilherme.githubreposlist.di.DaggerComponent
import br.com.guilherme.githubreposlist.ui.viewmodel.GitRepositoriesViewModel
import javax.inject.Inject

class GitReposFragment : Fragment(R.layout.fragment_git_repos) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModelGit: GitRepositoriesViewModel

    private var _binding: FragmentGitReposBinding? = null
    private val binding get() = _binding!!

    private var reposAdapter: GitReposAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGitReposBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerComponent.create().inject(this)
        setUpRecycler()
        initViewModel()
        observeViewModel()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecycler() {
        binding.reposRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            reposAdapter = GitReposAdapter(arrayListOf())
            adapter = reposAdapter
        }
    }

    private fun initViewModel() {
        viewModelGit =
            ViewModelProvider(this, viewModelFactory)[GitRepositoriesViewModel::class.java]
        viewModelGit.fetchRepos()
    }

    private fun observeViewModel() {
        viewModelGit.apply {
            error.observe(viewLifecycleOwner) {
                showError(it)
            }

            gitRepos.observe(viewLifecycleOwner) {
                fillGitRepos(it)
            }
        }
    }

    private fun fillGitRepos(repos: List<GitRepository>) {
        reposAdapter?.addAll(repos as ArrayList<GitRepository>)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}