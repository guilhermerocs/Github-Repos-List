package br.com.guilherme.githubreposlist.domain.usecases

import br.com.guilherme.githubreposlist.domain.repository.GitReposRepository
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCase
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCaseI
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
class FetchReposUseCaseTest {

    @Mock
    lateinit var gitReposRepository: GitReposRepository

    lateinit var sut: FetchReposUseCaseI

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        sut = FetchReposUseCase(gitReposRepository)
    }


    @Test
    fun shouldRepositoryResultCorrectly() {
        runBlockingTest {
            val fakeResult = generateReposList()

            val fakeResponse = flow {
                emit(fakeResult)
            }

            Mockito.`when`(gitReposRepository.fetchRepos()).thenReturn(fakeResponse)


            val useCaseResponse = sut.execute()

            useCaseResponse.collect {
                Assert.assertArrayEquals(it?.toTypedArray(), fakeResult.toTypedArray())
            }
        }
    }


}