package com.github.tak8997.coinranking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.AppRepository
import com.github.tak8997.coinranking.data.repository.Listing
import com.github.tak8997.coinranking.data.repository.NetworkState
import com.github.tak8997.coinranking.data.repository.Status
import com.github.tak8997.coinranking.ui.coins.CoinsViewModel
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("UNCHECKED_CAST")
@RunWith(JUnit4::class)
class CoinsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: AppRepository
    private lateinit var coinsViewModel: CoinsViewModel
    private lateinit var pagesObserver: Observer<PagedList<Coin>>

    @Before
    fun setupCoinsViewModel() {
        MockitoAnnotations.initMocks(this)

//        println(TestUtil.getCoins().size)
        pagesObserver = mock(Observer::class.java) as Observer<PagedList<Coin>>

        repository = mock(AppRepository::class.java)

        coinsViewModel = CoinsViewModel(repository)
        coinsViewModel.pages.observeForever(pagesObserver)
    }

    @Test
    fun fetchCoinsFromRepository() {
        val listing = Listing(
            MutableLiveData(mockPagedList(TestUtil.getCoins())),
            MutableLiveData(NetworkState(Status.SUCCESS, ""))
        )

        `when`(repository.fetchCoins()).thenReturn(Single.just(listing))
        coinsViewModel.fetchCoins()

        val pagedList = getPagedList(listing)

        verify(pagesObserver).onChanged(pagedList)
    }

    private fun getPagedList(listing: Listing<Coin>): PagedList<Coin> {
        val observer = LoggingObserver<PagedList<Coin>>()
        listing.pages.observeForever(observer)
        assertThat(observer.value, `is`(notNullValue()))
        return observer.value!!
    }

    private fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }

    private class LoggingObserver<T> : Observer<T> {
        var value : T? = null
        override fun onChanged(t: T?) {
            this.value = t
        }
    }
}