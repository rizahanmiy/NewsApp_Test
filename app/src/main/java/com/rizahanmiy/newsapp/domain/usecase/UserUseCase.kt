package com.rizahanmiy.newsapp.domain.usecase

import com.rizahanmiy.newsapp.data.entities.NewsArticlesApi
import com.rizahanmiy.newsapp.data.entities.NewsSourceApi
import com.rizahanmiy.newsapp.domain.common.ObservableRxTransformer
import com.rizahanmiy.newsapp.domain.common.ResultState
import com.rizahanmiy.newsapp.domain.repository.UserRepository
import io.reactivex.Observable

class UserUseCase(
    private val transformerArticle: ObservableRxTransformer<ResultState<MutableList<NewsArticlesApi>>>,
    private val transformerEverything: ObservableRxTransformer<ResultState<MutableList<NewsArticlesApi>>>,
    private val transformerSource: ObservableRxTransformer<ResultState<MutableList<NewsSourceApi>>>,
    val repository: UserRepository
) {

    fun fetchArticle(param: HashMap<String, Any?>): Observable<ResultState<MutableList<NewsArticlesApi>>> {

        return repository.fetchNewsArticle(param).compose(transformerArticle)

    }

    fun fetchEverything(param: HashMap<String, Any?>): Observable<ResultState<MutableList<NewsArticlesApi>>> {
        return repository.fetchNewsEverything(param).compose(transformerEverything)
    }

    fun fetchSource(param: HashMap<String, Any?>): Observable<ResultState<MutableList<NewsSourceApi>>> {

        return repository.fetchNewsSource(param).compose(transformerSource)

    }

}