package com.tak8997.app_coroutine.di

import com.tak8997.app_coroutine.di.module.CoinDetailModule
import com.tak8997.app_coroutine.di.module.CoinsModule
import com.tak8997.app_coroutine.ui.coins.CoinsActivity
import com.tak8997.app_coroutine.ui.coindetail.CoinDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [CoinsModule::class])
    abstract fun coinsActivity(): CoinsActivity

    @ContributesAndroidInjector(modules = [CoinDetailModule::class])
    abstract fun coinDetailActivity(): CoinDetailActivity
}
