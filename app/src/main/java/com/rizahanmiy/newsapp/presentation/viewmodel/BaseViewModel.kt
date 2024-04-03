package com.rizahanmiy.newsapp.presentation.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher

open class BaseViewModel : ViewModel() {

    private  val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun  addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
    private fun clearDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposable()
    }
    protected fun <T> MediatorLiveData<T>.add(publisher: Publisher<T>) {
        addSource(LiveDataReactiveStreams.fromPublisher(publisher)) {
            postValue(it)
        }
    }
}