package com.rizahanmiy.newsapp.domain.repository

import com.rizahanmiy.newsapp.data.entities.NewsArticlesApi
import com.rizahanmiy.newsapp.data.entities.NewsSourceApi
import com.rizahanmiy.newsapp.domain.common.ResultState
import io.reactivex.Observable

interface UserRepository {

    fun fetchNewsArticle(param:HashMap<String,Any?>) : Observable<ResultState<MutableList<NewsArticlesApi>>>

    fun fetchNewsEverything(param:HashMap<String,Any?>) : Observable<ResultState<MutableList<NewsArticlesApi>>>

    fun fetchNewsSource(param:HashMap<String,Any?>) : Observable<ResultState<MutableList<NewsSourceApi>>>

}