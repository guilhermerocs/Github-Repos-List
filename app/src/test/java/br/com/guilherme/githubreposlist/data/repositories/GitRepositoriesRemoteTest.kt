package br.com.guilherme.githubreposlist.data.repositories

import br.com.guilherme.githubreposlist.data.ApiGithubInterface
import br.com.guilherme.githubreposlist.data.source.remote.GitRepositoriesRemote
import br.com.guilherme.githubreposlist.test_utils.generateReposList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class GitRepositoriesRemoteTest {

    @Mock
    lateinit var apiService: ApiGithubInterface

    lateinit var sut: GitRepositoriesRemote

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        sut = GitRepositoriesRemote(apiService)
    }


    @Test
    fun shouldReturnCorrectly() {
        runBlockingTest {
            val fakeResult = generateReposList()

            Mockito.`when`(apiService.fetchRepos()).thenReturn(fakeResult)

            val repositoryResponse = sut.fetchPublicRepositories()

            repositoryResponse.collect {
                Assert.assertArrayEquals(fakeResult.toTypedArray(), it?.toTypedArray())
            }

        }
    }


}