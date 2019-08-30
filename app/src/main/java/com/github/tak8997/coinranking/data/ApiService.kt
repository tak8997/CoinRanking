package com.github.tak8997.coinranking.data

import com.github.tak8997.coinranking.data.model.CoinBaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("coins")
    fun fetchCoins(
        @Query("offset") offset: Int,
        @Query("limit") requestedLoadSize: Int
    ) : Single<CoinBaseResponse>
}