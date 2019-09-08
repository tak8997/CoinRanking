package com.github.tak8997.coinranking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.AppRepository
import com.github.tak8997.coinranking.ui.coindetail.CoinDetailViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("UNCHECKED_CAST")
@RunWith(JUnit4::class)
class CoinDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testId = 0

    private lateinit var repository: AppRepository
    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var resultObserver: Observer<Coin>

    @Before
    fun setupViewModel() {
        MockitoAnnotations.initMocks(this)

        resultObserver = mock(Observer::class.java) as Observer<Coin>

        repository = mock(AppRepository::class.java)

        viewModel = CoinDetailViewModel(repository)
        viewModel.result.observeForever(resultObserver)
    }

    @Test
    fun fetchCoinFromRepository() {
        `when`(repository.fetchCoin(testId)).thenReturn(Single.just(TestUtil.getCoin(testId)))
        viewModel.fetchCoin(testId)

        verify(resultObserver).onChanged(TestUtil.getCoin(testId))
    }
}