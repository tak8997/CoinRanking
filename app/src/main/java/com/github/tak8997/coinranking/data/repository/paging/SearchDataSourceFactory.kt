package com.github.tak8997.coinranking.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.tak8997.coinranking.data.ApiService
import com.github.tak8997.coinranking.data.model.Coin
import io.reactivex.disposables.CompositeDisposable

class SearchDataSourceFactory(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : DataSource.Factory<Int, Coin>() {

    val sourceLiveData = MutableLiveData<SearchDataSource>()

    override fun create(): DataSource<Int, Coin> {
        val source = SearchDataSource(apiService, disposables)
        sourceLiveData.postValue(source)
        return source
    }
}