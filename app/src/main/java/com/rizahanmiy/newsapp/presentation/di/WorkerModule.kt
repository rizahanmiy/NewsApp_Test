package com.rizahanmiy.newsapp.presentation.di

import androidx.work.Worker
import com.rizahanmiy.newsapp.presentation.service.ChildWorkerFactory
import com.rizahanmiy.newsapp.presentation.service.Factory
import com.rizahanmiy.newsapp.presentation.service.WorkerApp
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out Worker>)

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(WorkerApp::class)
    internal abstract fun bindMyWorkerFactory(worker: Factory): ChildWorkerFactory

}