package br.com.guilherme.githubreposlist.data.model.response

import br.com.guilherme.githubreposlist.data.model.entity.GitRepository

data class GitRepositoriesResponse(
    val repositories: List<GitRepository>
)