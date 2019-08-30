package com.github.tak8997.coinranking.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.tak8997.coinranking.data.ApiService
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SearchDataSource(
    private val keyword: String,
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, Coin>() {

    val networkError = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Coin>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Coin>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Coin>) {}
}