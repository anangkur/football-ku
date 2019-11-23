package com.anangkur.kotlinexpertsubmission.feature.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import com.anangkur.kotlinexpertsubmission.data.model.Result
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

class FavouriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: FavouriteViewModel
    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavouriteViewModel(repository)
    }

    @Test
    fun selectAllEvent() {
        val fakeData = createDummyResultMatchFav()
        val response = MutableLiveData<Result<List<EventFavourite>>>()
        response.value = fakeData

        whenever(repository.selectAllEventFav()).thenReturn(response)

        val observer = mock<Observer<Result<List<EventFavourite>>>>()
        viewModel.selectAllEvent().observeForever(observer)
        viewModel.refreshData()
        val captor = ArgumentCaptor.forClass(Result::class.java)
        captor.run {
            verify(observer).onChanged(capture() as Result<List<EventFavourite>>?)
            Assert.assertNotNull(value)
        }
    }

    private fun createDummyResultMatchFav(): Result<List<EventFavourite>> {
        val matchList = ArrayList<EventFavourite>()
        matchList.add(EventFavourite())
        return Result.success(matchList)
    }
}