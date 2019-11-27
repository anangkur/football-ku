package com.anangkur.kotlinexpertsubmission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.data.local.LocalRepository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import com.anangkur.kotlinexpertsubmission.data.model.*
import com.anangkur.kotlinexpertsubmission.data.remote.RemoteRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteRepository: RemoteRepository
    @Mock
    private lateinit var localRepository: LocalRepository
    private lateinit var repository: Repository
    var listClass: Class<ArrayList<League>>? = ArrayList::class.java as Class<ArrayList<League>>

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        repository = Repository(remoteRepository, localRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun createDummyLeague() {
        val fakeData = createFakeDummyLeague()
        val response = MutableLiveData<List<League>>()
        response.value = fakeData

        whenever(localRepository.createDummyLeague()).thenReturn(response)

        val observer = mock<Observer<List<League>>>()
        repository.createDummyLeague().observeForever(observer)
        val captor = ArgumentCaptor.forClass(listClass)
        captor.run {
            verify(observer).onChanged(capture())
            Assert.assertNotNull(value)
        }
    }

    @Test
    fun getDetailLeague() {
        val resultSuccess = Result.success(ResponseLeagueDetail(arrayListOf()))
        val resultFailed:Result<ResponseLeagueDetail> = Result.error("")
        val resultLoading:Result<ResponseLeagueDetail> = Result.loading()
        runBlockingTest{
            whenever(remoteRepository.getDetailLeague("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(remoteRepository.getDetailLeague("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(remoteRepository.getDetailLeague("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun getNextMatch() {
        val resultSuccess = Result.success(ResponseMatch(arrayListOf()))
        val resultFailed:Result<ResponseMatch> = Result.error("")
        val resultLoading:Result<ResponseMatch> = Result.loading()
        runBlockingTest{
            whenever(remoteRepository.getNextMatch("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(remoteRepository.getNextMatch("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(remoteRepository.getNextMatch("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun getPrevMatch() {
        val resultSuccess = Result.success(ResponseMatch(arrayListOf()))
        val resultFailed:Result<ResponseMatch> = Result.error("")
        val resultLoading:Result<ResponseMatch> = Result.loading()
        runBlockingTest{
            whenever(remoteRepository.getPrevMatch("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(remoteRepository.getPrevMatch("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(remoteRepository.getPrevMatch("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun getDetailMatch() {
        val resultSuccess = Result.success(ResponseMatch(arrayListOf()))
        val resultFailed:Result<ResponseMatch> = Result.error("")
        val resultLoading:Result<ResponseMatch> = Result.loading()
        runBlockingTest{
            whenever(remoteRepository.getDetailMatch("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(remoteRepository.getDetailMatch("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(remoteRepository.getDetailMatch("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun getSearchMatch() {
        val resultSuccess = Result.success(ResponseSearchMatch(arrayListOf()))
        val resultFailed:Result<ResponseSearchMatch> = Result.error("")
        val resultLoading:Result<ResponseSearchMatch> = Result.loading()
        runBlockingTest{
            whenever(remoteRepository.getSearchMatch("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(remoteRepository.getSearchMatch("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(remoteRepository.getSearchMatch("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun getTeamDetail() {
        val resultSuccess = Result.success(ResponseTeamDetail(arrayListOf()))
        val resultFailed:Result<ResponseTeamDetail> = Result.error("")
        val resultLoading:Result<ResponseTeamDetail> = Result.loading()
        runBlockingTest{
            whenever(remoteRepository.getTeamDetail("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(remoteRepository.getTeamDetail("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(remoteRepository.getTeamDetail("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun insertEventFav() {
        val resultSuccess = Result.success(0L)
        val resultFailed:Result<Long> = Result.error("")
        val resultLoading:Result<Long> = Result.loading()
        runBlockingTest{
            whenever(localRepository.insertEventFav(EventFavourite())).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(localRepository.insertEventFav(EventFavourite())).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(localRepository.insertEventFav(EventFavourite())).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun selectAllEventFav() {
        val resultSuccess:Result<List<EventFavourite>> = Result.success(arrayListOf())
        val resultFailed:Result<List<EventFavourite>> = Result.error("")
        val resultLoading:Result<List<EventFavourite>> = Result.loading()
        runBlockingTest{
            whenever(localRepository.selectAllEventFav()).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(localRepository.selectAllEventFav()).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(localRepository.selectAllEventFav()).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun selectEventFavById() {
        val resultSuccess:Result<EventFavourite> = Result.success(EventFavourite())
        val resultFailed:Result<EventFavourite> = Result.error("")
        val resultLoading:Result<EventFavourite> = Result.loading()
        runBlockingTest{
            whenever(localRepository.selectEventById("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(localRepository.selectEventById("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(localRepository.selectEventById("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    @Test
    fun deleteEventFav() {
        val resultSuccess = Result.success(0L)
        val resultFailed:Result<Long> = Result.error("")
        val resultLoading:Result<Long> = Result.loading()
        runBlockingTest{
            whenever(localRepository.deleteEventFav("")).thenReturn(resultSuccess)
            assertNotNull(resultSuccess)
            assertEquals(resultSuccess.status, Result.Status.SUCCESS)
            assertNotNull(resultSuccess.data)

            whenever(localRepository.deleteEventFav("")).thenReturn(resultFailed)
            assertNotNull(resultFailed)
            assertEquals(resultFailed.status, Result.Status.ERROR)
            assertNotNull(resultFailed.message)

            whenever(localRepository.deleteEventFav("")).thenReturn(resultLoading)
            assertNotNull(resultLoading)
            assertEquals(resultLoading.status, Result.Status.LOADING)
        }
    }

    private fun createFakeDummyLeague(): List<League> {
        val leagueList = ArrayList<League>()
        leagueList.add(
            League(
                "",
                "",
                "",
                0,
                0
            )
        )
        return leagueList
    }
}