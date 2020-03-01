package com.tak8997.app_coroutine.di.module

import androidx.lifecycle.ViewModel
import com.tak8997.app_coroutine.di.key.ViewModelKey
import com.tak8997.app_coroutine.ui.coins.CoinsViewModel
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
