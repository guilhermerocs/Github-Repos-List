package br.com.guilherme.githubreposlist.presentation.view.repos_list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guilherme.githubreposlist.R
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.databinding.FragmentGitReposBinding
import br.com.guilherme.githubreposlist.presentation.viewmodel.GitRepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GitReposFragment : Fragment(R.layout.fragment_git_repos),
    SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {


    private val viewModelGit by viewModel<GitRepositoriesViewModel>()

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

            loading.observe(viewLifecycleOwner) {
                manageProgress(it)
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

    private fun manageProgress(loading: Boolean) {
        if (loading) {
            binding.reposRecycler.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.reposRecycler.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
        }
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
        resetAdapter()
        return false
    }

    private fun filter(query: String?) {
        if (query != null && query.isNotEmpty()) {
            val filtered = reposAdapter?.gitRepos?.filter { gitRepo ->
                gitRepo.full_name?.contains(query) ?: false
            }
            reposAdapter?.addAll(filtered as ArrayList<GitRepository>)
        } else
            resetAdapter()

    }

    private fun resetAdapter() {

        reposAdapter?.addAll(reposGeneral as ArrayList<GitRepository>)
    }

}
