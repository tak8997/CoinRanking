package com.github.tak8997.coinranking.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.tak8997.coinranking.LoadingState
import com.github.tak8997.coinranking.data.ApiService
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SearchDataSource(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, Coin>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Coin>) {
        networkState.postValue(NetworkState.LOADING)
        apiService
            .fetchCoins(0, params.requestedLoadSize)
            .subscribe({
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.data.coins, 0, 10)
            }, {
                networkState.postValue(NetworkState.LOADED)
                networkState.postValue(NetworkState.error(it.message))
            })
            .addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Coin>) {
        networkState.postValue(NetworkState.LOADING)
        apiService
            .fetchCoins(params.key, params.requestedLoadSize)
            .subscribe({
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.data.coins, params.key + 10)
            }, {
                networkState.postValue(NetworkState.LOADED)
                networkState.postValue(NetworkState.error(it.message))
            })
            .addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Coin>) {}
}