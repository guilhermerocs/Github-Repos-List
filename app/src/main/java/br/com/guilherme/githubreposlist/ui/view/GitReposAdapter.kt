package br.com.guilherme.githubreposlist.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.databinding.ItemGitReposBinding

class GitReposAdapter(
    var gitRepos: ArrayList<GitRepository>
) : RecyclerView.Adapter<GitReposAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemGitReposBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val gitRepo = gitRepos[position]
        holder.bindView(gitRepo)
    }

    override fun getItemCount(): Int {
        return gitRepos.size
    }

    internal fun addAll(list: ArrayList<GitRepository>) {
        gitRepos = arrayListOf()
        gitRepos = list
        notifyDataSetChanged()
    }

    fun clear() {
        gitRepos.clear()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: ItemGitReposBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val view: ItemGitReposBinding = itemView

        fun bindView(
            gitRepository: GitRepository
        ) {
            with(view) {
                repoName.text = gitRepository.full_name
                repoUsername.text = gitRepository.owner.login
            }
        }
    }
}