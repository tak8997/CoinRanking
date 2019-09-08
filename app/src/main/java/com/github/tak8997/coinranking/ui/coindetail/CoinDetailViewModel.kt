package com.github.tak8997.coinranking.ui.coindetail

import androidx.lifecycle.MutableLiveData
import com.github.tak8997.coinranking.BaseViewModel
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.AppRepository
import com.github.tak8997.coinranking.data.repository.NetworkState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val repository: AppRepository
): BaseViewModel() {

    val result = MutableLiveData<Coin>()
    val favorite = MutableLiveData<Boolean>(false)
    val networkState = MutableLiveData<NetworkState>()
    val websiteUrlVisible = MutableLiveData(false)

    fun fetchCoin(id: Int) {
        repository
            .fetchCoin(id)
            .subscribe(result::setValue) { networkState.value = NetworkState.error(it.message) }
            .addTo(disposables)
    }

    fun onIconClicked(selected: Boolean) {
        websiteUrlVisible.value = selected
    }

    fun setFavorite(selected: Boolean) {
        favorite.value = selected
    }
}