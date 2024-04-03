package com.rizahanmiy.newsapp.presentation.service

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider


class WorkerAppFactory @Inject constructor(
    private val workerFactories: Map<Class<out Worker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        val entry = workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factory = entry?.value
            ?: throw IllegalArgumentException("could not find worker: $workerClassName")
        return factory.get().create(appContext,workerParameters)
    }
}