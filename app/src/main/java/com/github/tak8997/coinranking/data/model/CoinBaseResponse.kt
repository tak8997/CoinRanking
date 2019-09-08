package com.github.tak8997.coinranking.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable


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
    val symbol: String?,
    val name: String?,
    val description: String?,
    val iconUrl: String?,
    val websiteUrl: String?,
    val volume: Long,
    val price: Float,
    var favorite: Boolean,
    val change: Float,
    val history: List<String>?
): Parcelable, BaseObservable() {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readFloat(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(iconUrl)
        parcel.writeString(websiteUrl)
        parcel.writeLong(volume)
        parcel.writeFloat(price)
        parcel.writeByte(if (favorite) 1 else 0)
        parcel.writeFloat(change)
        parcel.writeStringList(history)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}

