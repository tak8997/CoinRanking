package com.github.tak8997.coinranking

import com.github.tak8997.coinranking.data.model.Coin

object TestUtil {

    fun getCoins(): List<Coin> {
        return (0..4).map {
            Coin(it,
                "",
                "",
                "",
                "",
                "",
                10,
                10.0F,
                false,
                0F,
                mutableListOf()
            )
        }
    }

    fun getCoin(testId: Int): Coin {
        return Coin(
            testId,
            "",
            "",
            "",
            "",
            "",
            10,
            10.0F,
            false,
            0F,
            mutableListOf()
        )
    }
}