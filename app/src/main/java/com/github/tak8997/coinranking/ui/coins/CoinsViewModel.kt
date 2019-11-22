package com.github.tak8997.coinranking.ui.coins

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import com.github.tak8997.coinranking.BaseActivity
import com.github.tak8997.coinranking.BaseViewModel
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.data.repository.AppRepository
import com.github.tak8997.coinranking.data.repository.Listing
import javax.inject.Inject

class CoinsViewModel @Inject constructor(
    private val repository: AppRepository
): BaseViewModel() {

    private val repoResult = MutableLiveData<Listing<Coin>>(repository.fetchCoins())

    val pages = switchMap(repoResult) { it.pages }
    val networkState = switchMap(repoResult) { it.networkState }
    val returnedItem = MutableLiveData<Coin>()

    fun fetchCoins() {
        repoResult.value?.refresh?.invoke()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BaseActivity.REQUEST_DETAIL_CODE && resultCode == Activity.RESULT_OK) {
            val item = data?.getParcelableExtra<Coin>(BaseActivity.DEFAULT_PARAM)
            returnedItem.value = item
        }
    }
}