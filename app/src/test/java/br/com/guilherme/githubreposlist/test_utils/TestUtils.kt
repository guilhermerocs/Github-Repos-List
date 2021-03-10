package br.com.guilherme.githubreposlist.test_utils

import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.model.entity.Owner

fun generateReposList(): List<GitRepository> {
    return listOf(
        GitRepository(
            1,
            "",
            "",
            Owner("", 1, ""),
            ""
        )
    )
}