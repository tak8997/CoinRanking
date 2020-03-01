package com.tak8997.app_coroutine.data.repository

enum class Status {
    LOADING,
    SUCCESS,
    FAILED
}

data class NetworkState constructor(
    val status: Status,
    val msg: String? = null
) {

    companion object {
        val LOADING = NetworkState(Status.LOADING)
        val LOADED = NetworkState(Status.SUCCESS)
        val FAILED = NetworkState(Status.FAILED)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}