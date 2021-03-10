package br.com.guilherme.githubreposlist.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.guilherme.githubreposlist.domain.model.entity.GitRepository
import br.com.guilherme.githubreposlist.domain.usecase.FetchReposUseCaseI
import br.com.guilherme.githubreposlist.test_utils.generateReposList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import kotlin.Exception

@ExperimentalCoroutinesApi
class GitRepositoriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var gitReposUseCase: FetchReposUseCaseI

    @Mock
    lateinit var reposObserver: Observer<List<GitRepository>>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<String>

    private val coroutineDispatcher = TestCoroutineDispatcher()

    lateinit var sut: GitRepositoriesViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        sut = GitRepositoriesViewModel(coroutineDispatcher, gitReposUseCase)
        sut.error.observeForever(errorObserver)
        sut.gitRepos.observeForever(reposObserver)
        sut.loading.observeForever(loadingObserver)

    }


    @After
    fun tearDown() {
        sut.gitRepos.removeObserver(reposObserver)
        sut.loading.removeObserver(loadingObserver)
        sut.error.removeObserver(errorObserver)
    }


    @Test
    fun shouldCallGitReposUseCaseCorrectly() {

        sut.fetchRepos()

        Mockito.verify(loadingObserver).onChanged(true)
        coroutineDispatcher.runBlockingTest {
            Mockito.verify(gitReposUseCase, Times(1)).execute()
        }

    }


    @Test
    fun shouldCallShowErrorWhenUseCaseThrows() {
        val fakeMessage = "fakeMessage"

        val fakeFlow: Flow<List<GitRepository>> = flow {
            throw  Exception(fakeMessage)
        }

        coroutineDispatcher.runBlockingTest {
            Mockito.`when`(gitReposUseCase.execute()).thenReturn(fakeFlow)

            sut.fetchRepos()

            Mockito.verify(loadingObserver).onChanged(true)
            Mockito.verify(loadingObserver).onChanged(false)
            Mockito.verify(errorObserver).onChanged(fakeMessage)
        }
    }

    @Test
    fun shouldCallSuccessCorrectly() {
        val fakeResult = generateReposList()

        val fakeFlow = flow {
            emit(fakeResult)
        }

        coroutineDispatcher.runBlockingTest {
            Mockito.`when`(gitReposUseCase.execute()).thenReturn(fakeFlow)

            sut.fetchRepos()

            Mockito.verify(loadingObserver).onChanged(true)
            Mockito.verify(loadingObserver).onChanged(false)
            Mockito.verify(reposObserver).onChanged(fakeResult)

        }


    }




}