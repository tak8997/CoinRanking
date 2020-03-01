package com.tak8997.app_coroutine.ui.coindetail

import androidx.lifecycle.MutableLiveData
import com.tak8997.app_coroutine.BaseViewModel
import com.tak8997.app_coroutine.data.model.Coin
import com.tak8997.app_coroutine.data.repository.AppRepository
import com.tak8997.app_coroutine.data.repository.NetworkState
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
//            .subscribe(result::setValue) { networkState.value = NetworkState.error(it.message) }
//            .addTo(disposables)
    }

    fun onIconClicked(selected: Boolean) {
        websiteUrlVisible.value = selected
    }

    fun setFavorite(selected: Boolean) {
        favorite.value = selected
    }
}