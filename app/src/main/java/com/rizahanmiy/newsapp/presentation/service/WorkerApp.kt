package com.rizahanmiy.newsapp.presentation.service


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rizahanmiy.newsapp.domain.usecase.UserUseCase
import io.reactivex.disposables.CompositeDisposable

class WorkerApp(
    private val userUseCase: UserUseCase,
    val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun doWork(): Result {
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        compositeDisposable.clear()
    }

}