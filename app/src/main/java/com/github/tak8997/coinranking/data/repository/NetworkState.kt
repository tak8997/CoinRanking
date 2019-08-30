package com.github.tak8997.coinranking.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState constructor(
    val status: Status,
    val msg: String? = null
) {

    companion object {
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}