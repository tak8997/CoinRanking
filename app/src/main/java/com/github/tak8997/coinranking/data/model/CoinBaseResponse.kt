package com.github.tak8997.coinranking.data.model


data class CoinBaseResponse(
    val status: String,
    val data: Data
)

data class Data(
    val stats: Stats,
    val base: Base,
    val coin: Coin,
    val coins: MutableList<Coin>
)

data class Stats(
    val total: Int,
    val offset: Int,
    val limit: Int,
    val order: String,
    val base: String,
    val totalMarkets: Int,
    val totalExchanges: Int,
    val totalMarketCap: Float,
    val total24hVolume: Float
)

data class Base(
    val symbol: String,
    val sign: String
)

data class Coin(
    val id: Int,
    val symbol: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val volume: Long,
    val price: Float,
    val favorite: Boolean,
    val change: Float,
    val history: List<String>
)

