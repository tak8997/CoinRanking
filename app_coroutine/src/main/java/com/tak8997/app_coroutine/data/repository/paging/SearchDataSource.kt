package com.tak8997.app_coroutine.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.tak8997.app_coroutine.data.ApiService
import com.tak8997.app_coroutine.data.model.Coin
import com.tak8997.app_coroutine.data.repository.NetworkState

class SearchDataSource(
    private val apiService: ApiService
) : PageKeyedDataSource<Int, Coin>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Coin>) {
        networkState.postValue(NetworkState.LOADING)
        apiService
            .fetchCoins(0, params.requestedLoadSize)
//            .subscribe({
//                networkState.postValue(NetworkState.LOADED)
//                callback.onResult(it.data.coins, 0, 10)
//            }, {
//                networkState.postValue(NetworkState.LOADED)
//                networkState.postValue(NetworkState.error(it.message))
//            })
//            .addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Coin>) {
        networkState.postValue(NetworkState.LOADING)
        apiService
            .fetchCoins(params.key, params.requestedLoadSize)
//            .subscribe({
//                networkState.postValue(NetworkState.LOADED)
//                callback.onResult(it.data.coins, params.key + 10)
//            }, {
//                networkState.postValue(NetworkState.LOADED)
//                networkState.postValue(NetworkState.error(it.message))
//            })
//            .addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Coin>) {}
}