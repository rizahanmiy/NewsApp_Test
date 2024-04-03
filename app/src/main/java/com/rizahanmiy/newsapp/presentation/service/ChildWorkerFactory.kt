package com.rizahanmiy.newsapp.presentation.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rizahanmiy.newsapp.domain.usecase.UserUseCase
import javax.inject.Inject

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): Worker
}

class Factory @Inject constructor(
    val userUseCase: UserUseCase
): ChildWorkerFactory {

    override fun create(appContext: Context, params: WorkerParameters): Worker {
        return WorkerApp(userUseCase, appContext, params)
    }
}