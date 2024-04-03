package com.rizahanmiy.newsapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rizahanmiy.newsapp.data.entities.NewsArticlesApi
import com.rizahanmiy.newsapp.data.entities.NewsSourceApi
import com.rizahanmiy.newsapp.domain.common.ResultState
import com.rizahanmiy.newsapp.domain.usecase.UserUseCase
import javax.inject.Inject
import kotlin.collections.HashMap


class NewsViewModel @Inject constructor(private val useCase: UserUseCase) : BaseViewModel() {

    fun fetchArticle(page: Int?, pageSize: Int?, country: String? = "", category: String? = "",search: String? = ""): MutableLiveData<ResultState<MutableList<NewsArticlesApi>>> {
        val fetchResult = MutableLiveData<ResultState<MutableList<NewsArticlesApi>>>()
        val param = HashMap<String, Any?>()
        param["page"] = page
        param["pageSize"] = pageSize
        param["country"] = country
        param["category"] = category
        param["q"] = search

        val disposable = useCase.fetchArticle(param)
            .doOnSubscribe{
                fetchResult.postValue(ResultState.Loading())
            }
            .subscribe{ resultState ->
                fetchResult.postValue(resultState)
            }
        addDisposable(disposable)
        return fetchResult
    }

    fun fetchArticleSources(page: Int?, pageSize: Int?, sources: String? = "", search: String? = ""): MutableLiveData<ResultState<MutableList<NewsArticlesApi>>> {
        val fetchResult = MutableLiveData<ResultState<MutableList<NewsArticlesApi>>>()
        val param = HashMap<String, Any?>()
        param["page"] = page
        param["pageSize"] = pageSize
        param["sources"] = sources
        param["q"] = search

        val disposable = useCase.fetchEverything(param)
            .doOnSubscribe{
                fetchResult.postValue(ResultState.Loading())
            }
            .subscribe{ resultState ->
                fetchResult.postValue(resultState)
            }
        addDisposable(disposable)
        return fetchResult
    }

    fun fetchSource(language: String = "",country: String? = "", category: String? = ""): MutableLiveData<ResultState<MutableList<NewsSourceApi>>> {
        val fetchResult = MutableLiveData<ResultState<MutableList<NewsSourceApi>>>()
        val param = HashMap<String, Any?>()
        param["language"] = language
        param["country"] = country
        param["category"] = category

        val disposable = useCase.fetchSource(param)
            .doOnSubscribe{
                fetchResult.postValue(ResultState.Loading())
            }
            .subscribe{ resultState ->
                fetchResult.postValue(resultState)
            }
        addDisposable(disposable)
        return fetchResult
    }
}