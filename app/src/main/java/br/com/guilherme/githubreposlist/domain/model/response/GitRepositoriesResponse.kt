package br.com.guilherme.githubreposlist.domain.model.response

import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository

data class GitRepositoriesResponse(
    val repositories: List<GitRepository>
)