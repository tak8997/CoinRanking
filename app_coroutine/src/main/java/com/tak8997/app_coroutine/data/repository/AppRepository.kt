package com.tak8997.app_coroutine.data.repository

import com.tak8997.app_coroutine.data.model.Coin
import com.tak8997.app_coroutine.data.model.CoinBaseResponse
import retrofit2.Call

interface AppRepository {

    fun fetchCoins(): Listing<Coin>
    fun fetchCoin(id: Int): Call<CoinBaseResponse>
}