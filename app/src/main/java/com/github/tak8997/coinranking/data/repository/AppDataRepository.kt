package com.github.tak8997.coinranking.data.repository

import com.github.tak8997.coinranking.data.ApiService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
): AppRepository {

}