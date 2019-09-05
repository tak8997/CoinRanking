package com.github.tak8997.coinranking.ui.coindetail

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.github.tak8997.coinranking.BaseViewModel
import com.github.tak8997.coinranking.R
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.AppDataRepository
import com.github.tak8997.coinranking.data.repository.NetworkState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val repository: AppDataRepository
): BaseViewModel() {

    val result = MutableLiveData<Coin>()
    val networkState = MutableLiveData<NetworkState>()
    val websiteUrlVisible = MutableLiveData(false)

    fun fetchCoin(id: Int) {
        repository
            .fetchCoin(id)
            .subscribe(result::setValue) { networkState.value = NetworkState.error(it.message) }
            .addTo(disposables)
    }

    fun onClick(view: View) {
        when(view.id) {
            R.id.favorite -> result.value = result.value?.apply { favorite_ = !view.isSelected }
            R.id.icon -> websiteUrlVisible.value = !view.isSelected
        }
    }
}