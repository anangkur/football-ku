package com.anangkur.footbalku.feature.leagueDetail.nextMatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertNotNull
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NextMatchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: NextMatchViewModel
    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = NextMatchViewModel(repository)
    }

    @Test
    fun getNextMatch() {
        val fakeData = createDummyResult()
        val response = MutableLiveData<Result<ResponseMatch>>()
        response.value = fakeData

        whenever(repository.getNextMatch("")).thenReturn(response)

        val observer = mock<Observer<Result<ResponseMatch>>>()
        viewModel.getNextMatch().observeForever(observer)
        viewModel.refreshData()
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<ResponseMatch>?)
            assertNotNull(value)
        }
    }

    private fun createDummyResult(): Result<ResponseMatch> {
        val eventList = ArrayList<Event>()
        eventList.add(Event())
        return Result.success(ResponseMatch(eventList))
    }
}