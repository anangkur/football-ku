package com.anangkur.kotlinexpertsubmission.feature.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class LeagueViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: LeagueViewModel
    @Mock private lateinit var repository: Repository
    var listClass: Class<ArrayList<League>>? = ArrayList::class.java as Class<ArrayList<League>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LeagueViewModel(repository)
    }

    @Test
    fun getDummyData() {
        val fakeData = createDummyLeague()
        val response = MutableLiveData<List<League>>()
        response.value = fakeData

        whenever(repository.createDummyLeague()).thenReturn(response)

        val observer = mock<Observer<List<League>>>()
        viewModel.getDummyData().observeForever(observer)
        val captor = ArgumentCaptor.forClass(listClass)
        captor.run {
            verify(observer).onChanged(capture())
            assertNotNull(value)
        }
    }

    private fun createDummyLeague(): List<League> {
        val leagueList = ArrayList<League>()
        leagueList.add(League(
            "",
            "",
            "",
            0,
            0
        ))
        return leagueList
    }
}