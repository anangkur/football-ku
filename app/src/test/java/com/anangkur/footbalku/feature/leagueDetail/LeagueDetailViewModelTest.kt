package com.anangkur.footbalku.feature.leagueDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.LeagueDetail
import com.anangkur.footbalku.data.model.ResponseLeagueDetail
import com.anangkur.footbalku.data.model.Result
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

class LeagueDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: LeagueDetailViewModel
    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LeagueDetailViewModel(repository)
    }

    @Test
    fun getLeagueDetail() {
        val fakeData = createDummyResult()
        val response = MutableLiveData<Result<ResponseLeagueDetail>>()
        response.value = fakeData

        whenever(repository.getDetailLeague("")).thenReturn(response)

        val observer = mock<Observer<Result<ResponseLeagueDetail>>>()
        viewModel.getLeagueDetail().observeForever(observer)
        viewModel.refreshData()
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<ResponseLeagueDetail>?)
            assertNotNull(value)
        }
    }

    private fun createDummyResult(): Result<ResponseLeagueDetail> {
        val leagueList = ArrayList<LeagueDetail>()
        leagueList.add(LeagueDetail())
        return Result.success(ResponseLeagueDetail(leagueList))
    }
}