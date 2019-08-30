package com.github.tak8997.coinranking.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.tak8997.coinranking.BaseViewModelFactory
import com.github.tak8997.coinranking.util.AppSchedulerProvider
import com.github.tak8997.coinranking.util.SchedulerProvider
import com.github.tak8997.coinranking.data.repository.AppDataRepository
import com.github.tak8997.coinranking.data.repository.AppRepository
import com.github.tak8997.coinranking.di.qualifier.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
interface AppModule {
    @Module
    class ProvideModule {

        @Singleton
        @Provides
        @App
        fun provideContext(): Context = com.github.tak8997.coinranking.App.instance

        @Provides
        @Singleton
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }

    @Binds
    @Singleton
    fun bindsAppRepository(repository: AppDataRepository): AppRepository

    @Binds
    @Singleton
    fun bindsSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindsViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}