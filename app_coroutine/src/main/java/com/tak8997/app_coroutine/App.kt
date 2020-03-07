package com.tak8997.app_coroutine

import android.app.Application
import com.facebook.stetho.Stetho
import com.tak8997.app_coroutine.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    companion object {
        lateinit var instance: App
            private set
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = activityInjector

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupStetho()
        setupDagger()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupDagger() {
        DaggerAppComponent
            .builder()
            .create(this)
            .inject(this)
    }
}