package com.anangkur.footbalku.feature.matchSearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.Event
import com.anangkur.footbalku.data.model.ResponseSearchMatch
import com.anangkur.footbalku.data.model.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: SearchViewModel
    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun getSearchMatch() {
        val fakeData = createDummyResult()
        val response = MutableLiveData<Result<ResponseSearchMatch>>()
        response.value = fakeData

        whenever(repository.getSearchMatch("")).thenReturn(response)

        val observer = mock<Observer<Result<ResponseSearchMatch>>>()
        viewModel.getSearchMatch().observeForever(observer)
        viewModel.refreshData("")
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<ResponseSearchMatch>?)
            Assert.assertNotNull(value)
        }
    }

    private fun createDummyResult(): Result<ResponseSearchMatch> {
        val eventList = ArrayList<Event>()
        eventList.add(Event())
        return Result.success(ResponseSearchMatch(eventList))
    }
}