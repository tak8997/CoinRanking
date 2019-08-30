package com.github.tak8997.coinranking.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import com.github.tak8997.coinranking.BaseViewModel
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.AppDataRepository
import com.github.tak8997.coinranking.data.repository.Listing
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CoinsViewModel @Inject constructor(
    private val repository: AppDataRepository
): BaseViewModel() {

    private val pageResult =  MutableLiveData<Listing<Coin>>()

    val pages = switchMap(pageResult) { it.pages }
    val networkErrors = switchMap(pageResult) { it.networkState }

    fun fetchCoins() {
        repository.fetchCoins()
            .subscribe(pageResult::postValue)
            .addTo(disposables)
    }
}