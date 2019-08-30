package com.github.tak8997.coinranking.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.tak8997.coinranking.data.ApiService
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.paging.SearchDataSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
): AppRepository {

    override fun fetchCoins(): Single<Listing<Coin>> {
        val dataSourceFactory = SearchDataSourceFactory(apiService, disposables)

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        val networkError = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.networkError
        }

        return Single.just(Listing(
            LivePagedListBuilder(dataSourceFactory, config).build(),
            networkError
        ))
    }
}