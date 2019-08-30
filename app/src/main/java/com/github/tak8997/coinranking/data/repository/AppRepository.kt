package com.github.tak8997.coinranking.data.repository

import com.github.tak8997.coinranking.data.model.Coin
import io.reactivex.Single

interface AppRepository {

    fun fetchCoins(): Single<Listing<Coin>>
    fun fetchCoin(id: Int): Single<Coin>
}