package br.com.guilherme.githubreposlist.ui.view.repos_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.guilherme.githubreposlist.R
import br.com.guilherme.githubreposlist.databinding.FragmentGitRepoDetailBinding
import javax.inject.Inject

class GitRepoDetailFragment : Fragment(R.layout.fragment_git_repo_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: FragmentGitRepoDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGitRepoDetailBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}