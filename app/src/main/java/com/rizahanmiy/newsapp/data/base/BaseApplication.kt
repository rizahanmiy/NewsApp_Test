package com.rizahanmiy.newsapp.data.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.work.Configuration
import androidx.work.WorkManager
import com.rizahanmiy.newsapp.presentation.di.DaggerAppComponent
import com.rizahanmiy.newsapp.presentation.service.WorkerAppFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector, Configuration.Provider {


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var myWorkerFactory: WorkerAppFactory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        val config = Configuration.Builder()
            .setWorkerFactory(myWorkerFactory)
            .build()
        WorkManager.initialize(this, config)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        val config = Configuration.Builder()
            .setWorkerFactory(myWorkerFactory)
            .build()
        WorkManager.initialize(this, config)
        return config
    }
}