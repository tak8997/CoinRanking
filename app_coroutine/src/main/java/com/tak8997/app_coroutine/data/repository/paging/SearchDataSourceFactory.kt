package com.tak8997.app_coroutine.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tak8997.app_coroutine.data.ApiService
import com.tak8997.app_coroutine.data.model.Coin

class SearchDataSourceFactory(
    private val apiService: ApiService
) : DataSource.Factory<Int, Coin>() {

    val sourceLiveData = MutableLiveData<SearchDataSource>()

    override fun create(): DataSource<Int, Coin> {
        val source = SearchDataSource(apiService)
        sourceLiveData.postValue(source)
        return source
    }
}