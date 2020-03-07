package com.tak8997.app_coroutine.di

import com.tak8997.app_coroutine.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class,
    AppNetworkModule::class
])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>()
}