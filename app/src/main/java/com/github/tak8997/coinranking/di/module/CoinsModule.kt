package com.github.tak8997.coinranking.di.module

import androidx.lifecycle.ViewModel
import com.github.tak8997.coinranking.di.key.ViewModelKey
import com.github.tak8997.coinranking.ui.CoinsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CoinsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinsViewModel::class)
    fun bindsViewModel(coinsViewModel: CoinsViewModel): ViewModel
}
