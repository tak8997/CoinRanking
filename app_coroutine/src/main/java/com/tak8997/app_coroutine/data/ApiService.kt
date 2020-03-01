package com.tak8997.app_coroutine.data

import com.tak8997.app_coroutine.data.model.CoinBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coins")
    fun fetchCoins(
        @Query("offset") offset: Int,
        @Query("limit") requestedLoadSize: Int
    ) : Call<CoinBaseResponse>

    @GET("coin/{coin_id}")
    fun fetchCoin(@Path("coin_id") id: Int): Call<CoinBaseResponse>
}