package com.github.tak8997.coinranking.di

import com.github.tak8997.coinranking.di.module.CoinDetailModule
import com.github.tak8997.coinranking.di.module.CoinsModule
import com.github.tak8997.coinranking.ui.CoinsActivity
import com.github.tak8997.coinranking.ui.coindetail.CoinDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [CoinsModule::class])
    abstract fun coinsActivity(): CoinsActivity

    @ContributesAndroidInjector(modules = [CoinDetailModule::class])
    abstract fun coinDetailActivity(): CoinDetailActivity
}
