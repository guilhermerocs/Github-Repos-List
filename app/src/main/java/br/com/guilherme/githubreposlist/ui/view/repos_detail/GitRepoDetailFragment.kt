package br.com.guilherme.githubreposlist.ui.view.repos_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.guilherme.githubreposlist.R
import br.com.guilherme.githubreposlist.databinding.FragmentGitRepoDetailBinding
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.util.extensions.loadImage
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getArgs() {
        arguments?.let { args ->
            val repo: GitRepository? = args.getParcelable("repo")
            repo?.let {
                with(it) {
                    setUpLayout(
                        owner.avatar_url,
                        owner.login,
                        name,
                        description
                    )
                }
            }
        }
    }


    private fun setUpLayout(
        mAvatarUrl: String?,
        mOwnerName: String?,
        mRepoName: String?,
        mRepoDesc: String?
    ) {

        with(binding) {
            repoDesc.text = mRepoDesc
            repoName.text = mRepoName
            ownerName.text = mOwnerName
            ownerAvatar.loadImage(mAvatarUrl)
        }

    }

}