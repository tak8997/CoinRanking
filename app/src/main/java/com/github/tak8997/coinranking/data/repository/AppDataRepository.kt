package com.github.tak8997.coinranking.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.tak8997.coinranking.data.ApiService
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.paging.SearchDataSourceFactory
import com.github.tak8997.coinranking.util.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable,
    private val scheduler: SchedulerProvider
): AppRepository {

    override fun fetchCoins(): Listing<Coin> {
        val dataSourceFactory = SearchDataSourceFactory(apiService, disposables)

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.networkState
        }

        return Listing(
            pages = LivePagedListBuilder(dataSourceFactory, config).build(),
            networkState = networkState,
            refresh = { dataSourceFactory.sourceLiveData.value?.invalidate() }
        )
    }

    override fun fetchCoin(id: Int): Single<Coin> {
        return apiService.fetchCoin(id)
            .map { it.data.coin }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }
}