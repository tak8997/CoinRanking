package com.github.tak8997.coinranking.di.module

import androidx.lifecycle.ViewModel
import com.github.tak8997.coinranking.di.key.ViewModelKey
import com.github.tak8997.coinranking.ui.coindetail.CoinDetailViewModel
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
