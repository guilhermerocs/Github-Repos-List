package br.com.guilherme.githubreposlist.data.repositories

import br.com.guilherme.githubreposlist.data.repository.GitReposRepositoryImp
import br.com.guilherme.githubreposlist.data.source.remote.GitRepositoriesRemote
import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import br.com.guilherme.githubreposlist.test_utils.generateReposList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class GitReposRepositoryTest {

    @Mock
    lateinit var remote: GitRepositoriesRemote

    lateinit var sut: GitReposRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        sut = GitReposRepositoryImp(remote)
    }


    @Test
    fun shouldReturnCorrectly() {
        runBlockingTest {
            val fakeResult = generateReposList()

            val fakeResponse = flow {
                emit(fakeResult)
            }

            Mockito.`when`(remote.fetchPublicRepositories()).thenReturn(fakeResponse)

            val repositoryResponse = sut.fetchRepos()

            repositoryResponse.collect {
                Assert.assertArrayEquals(fakeResult.toTypedArray(), it?.toTypedArray())
            }

        }
    }

}