package br.com.guilherme.githubreposlist.ui.view.repos_list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guilherme.githubreposlist.R
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.databinding.FragmentGitReposBinding
import br.com.guilherme.githubreposlist.di.DaggerComponent
import br.com.guilherme.githubreposlist.ui.viewmodel.GitRepositoriesViewModel
import javax.inject.Inject

class GitReposFragment : Fragment(R.layout.fragment_git_repos),
    SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModelGit: GitRepositoriesViewModel

    private var _binding: FragmentGitReposBinding? = null
    private val binding get() = _binding!!

    private var reposAdapter: GitReposAdapter? = null

    private var searchView: SearchView? = null

    private var reposGeneral: List<GitRepository>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
        searchView = menu.findItem(R.id.search).actionView as? SearchView
        searchView?.setOnQueryTextListener(this)
    }

    private fun setUpRecycler() {
        binding.reposRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            reposAdapter = GitReposAdapter(arrayListOf(), ::navigateToDetail)
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
        reposGeneral = repos
        reposAdapter?.addAll(repos as ArrayList<GitRepository>)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    private fun navigateToDetail(gitRepository: GitRepository) {
        val bundle = bundleOf("repo" to gitRepository)
        findNavController().navigate(R.id.gitRepoDetailFragment, bundle)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        filter(newText)
        return false
    }

    override fun onClose(): Boolean {
        reposAdapter?.addAll(reposGeneral as ArrayList<GitRepository>)
        return false
    }

    private fun filter(query: String?) {
        if (query != null && query.isNotEmpty()) {
            val filtered = reposAdapter?.gitRepos?.filter { it.full_name.contains(query) }
            reposAdapter?.addAll(filtered as ArrayList<GitRepository>)
        } else {
            reposAdapter?.addAll(reposGeneral as ArrayList<GitRepository>)
        }
    }

}
