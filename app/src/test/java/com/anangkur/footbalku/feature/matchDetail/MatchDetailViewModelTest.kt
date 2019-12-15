package com.anangkur.footbalku.feature.matchDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.local.ankoSqlite.EventFavourite
import com.anangkur.footbalku.data.model.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MatchDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: MatchDetailViewModel
    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MatchDetailViewModel(repository)
    }

    @Test
    fun getDetailMatch() {
        val fakeData = createDummyResultMatch()
        val response = MutableLiveData<Result<ResponseMatch>>()
        response.value = fakeData

        whenever(repository.getDetailMatch("")).thenReturn(response)

        val observer = mock<Observer<Result<ResponseMatch>>>()
        viewModel.getDetailMatch().observeForever(observer)
        viewModel.refreshData()
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<ResponseMatch>?)
            Assert.assertNotNull(value)
        }
    }

    @Test
    fun getHomeTeam() {
        val fakeData = createDummyResultTeam()
        val response = MutableLiveData<Result<ResponseTeamDetail>>()
        response.value = fakeData

        whenever(repository.getTeamDetail("")).thenReturn(response)

        val observer = mock<Observer<Result<ResponseTeamDetail>>>()
        viewModel.getHomeTeam().observeForever(observer)
        viewModel.refreshData()
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<ResponseTeamDetail>?)
            Assert.assertNotNull(value)
        }
    }

    @Test
    fun getAwayTeam() {
        val fakeData = createDummyResultTeam()
        val response = MutableLiveData<Result<ResponseTeamDetail>>()
        response.value = fakeData

        whenever(repository.getTeamDetail("")).thenReturn(response)

        val observer = mock<Observer<Result<ResponseTeamDetail>>>()
        viewModel.getAwayTeam().observeForever(observer)
        viewModel.refreshData()
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<ResponseTeamDetail>?)
            Assert.assertNotNull(value)
        }
    }

    @Test
    fun selectEventById() {
        val fakeData = createDummyResultFav()
        val response = MutableLiveData<Result<EventFavourite>>()
        response.value = fakeData

        whenever(repository.selectEventFavById("")).thenReturn(response)

        val observer = mock<Observer<Result<EventFavourite>>>()
        viewModel.selectEventById().observeForever(observer)
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<EventFavourite>?)
            Assert.assertNotNull(value)
        }
    }

    @Test
    fun insertEvent() {
        val fakeData = Result.success(0L)
        val response = MutableLiveData<Result<Long>>()
        response.value = fakeData

        whenever(repository.insertEventFav(EventFavourite())).thenReturn(response)

        val observer = mock<Observer<Result<Long>>>()
        viewModel.insertEvent().observeForever(observer)
        viewModel.insertEventData(EventFavourite())
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<Long>?)
            Assert.assertNotNull(value)
        }
    }

    @Test
    fun deleteEvent() {
        val fakeData = Result.success(0L)
        val response = MutableLiveData<Result<Long>>()
        response.value = fakeData

        whenever(repository.deleteEventFav("")).thenReturn(response)

        val observer = mock<Observer<Result<Long>>>()
        viewModel.deleteEvent().observeForever(observer)
        viewModel.deleteEventData(EventFavourite())
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<Long>?)
            Assert.assertNotNull(value)
        }
    }

    private fun createDummyResultTeam(): Result<ResponseTeamDetail> {
        val teamList = ArrayList<Team>()
        teamList.add(Team())
        return Result.success(ResponseTeamDetail(teamList))
    }

    private fun createDummyResultMatch(): Result<ResponseMatch> {
        val matchList = ArrayList<Event>()
        matchList.add(Event())
        return Result.success(ResponseMatch(matchList))
    }

    private fun createDummyResultFav(): Result<EventFavourite> {
        return Result.success(EventFavourite())
    }
}