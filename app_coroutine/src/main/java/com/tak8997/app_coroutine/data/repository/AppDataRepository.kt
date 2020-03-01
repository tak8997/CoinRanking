package com.tak8997.app_coroutine.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tak8997.app_coroutine.data.ApiService
import com.tak8997.app_coroutine.data.model.Coin
import com.tak8997.app_coroutine.data.model.CoinBaseResponse
import com.tak8997.app_coroutine.data.repository.paging.SearchDataSourceFactory
import retrofit2.Call
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService
): AppRepository {

    override fun fetchCoins(): Listing<Coin> {
        val dataSourceFactory = SearchDataSourceFactory(apiService)

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

    override fun fetchCoin(id: Int): Call<CoinBaseResponse> {
        return apiService.fetchCoin(id)
    }
}