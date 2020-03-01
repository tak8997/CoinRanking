package com.tak8997.app_coroutine.di.module

import androidx.lifecycle.ViewModel
import com.tak8997.app_coroutine.di.key.ViewModelKey
import com.tak8997.app_coroutine.ui.coindetail.CoinDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CoinDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    fun bindsViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel
}
