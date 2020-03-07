package com.tak8997.app_coroutine.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.tak8997.coinranking.BaseViewModelFactory
import com.tak8997.app_coroutine.data.repository.AppDataRepository
import com.tak8997.app_coroutine.data.repository.AppRepository
import com.tak8997.app_coroutine.di.qualifier.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
interface AppModule {

    @Module
    class ProvideModule {

        @Singleton
        @Provides
        @App
        fun provideContext(): Context = com.tak8997.app_coroutine.App.instance
    }

    @Binds
    @Singleton
    fun bindsAppRepository(repository: AppDataRepository): AppRepository

    @Binds
    fun bindsViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}