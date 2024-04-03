package com.rizahanmiy.newsapp.utils.common

import com.rizahanmiy.newsapp.domain.common.ObservableRxTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.schedulers.Schedulers

class AsyncObservableTransformer<T> : ObservableRxTransformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> = upstream.subscribeOn(Schedulers.io())
}