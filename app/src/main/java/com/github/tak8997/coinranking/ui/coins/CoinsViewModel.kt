package com.github.tak8997.coinranking.ui.coins

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import com.github.tak8997.coinranking.BaseActivity
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
    val returnedItem = MutableLiveData<Coin>()

    fun fetchCoins() {
        repository.fetchCoins()
            .subscribe(pageResult::postValue)
            .addTo(disposables)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BaseActivity.REQUEST_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            val item = data?.getParcelableExtra<Coin>(BaseActivity.DEFAULT_PARAM)
            returnedItem.value = item
        }
    }
}