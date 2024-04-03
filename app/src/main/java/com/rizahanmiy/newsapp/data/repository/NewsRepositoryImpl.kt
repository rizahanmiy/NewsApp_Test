package com.rizahanmiy.newsapp.data.repository

import android.content.Context
import com.rizahanmiy.newsapp.BuildConfig
import com.rizahanmiy.newsapp.data.api.RemoteApi
import com.rizahanmiy.newsapp.data.entities.NewsArticlesApi
import com.rizahanmiy.newsapp.data.entities.NewsSourceApi
import com.rizahanmiy.newsapp.domain.common.ResultState
import com.rizahanmiy.newsapp.domain.repository.UserRepository
import com.rizahanmiy.newsapp.utils.common.handleApiError
import com.rizahanmiy.newsapp.utils.common.handleApiSuccess
import io.reactivex.Observable

class NewsRepositoryImpl(
    private val api: RemoteApi,
    val context: Context
) : UserRepository {

    override fun fetchNewsArticle(param: HashMap<String, Any?>): Observable<ResultState<MutableList<NewsArticlesApi>>> {
        param["apiKey"] = BuildConfig.API_KEY
        return api.fetchNewsArticles(param)
            .map { data ->
                handleApiSuccess(
                    status = data.status,
                    data = data.articles,
                    totalResults = null
                )
            }.onErrorReturn { e ->
                handleApiError(e)
            }
    }

    override fun fetchNewsEverything(param: HashMap<String, Any?>): Observable<ResultState<MutableList<NewsArticlesApi>>> {
        param["apiKey"] = BuildConfig.API_KEY
        return api.fetchNewsArticles(param)
            .map { data ->
                handleApiSuccess(
                    status = data.status,
                    data = data.articles,
                    totalResults = null
                )
            }.onErrorReturn { e ->
                handleApiError(e)
            }
    }

    override fun fetchNewsSource(param: HashMap<String, Any?>): Observable<ResultState<MutableList<NewsSourceApi>>> {
        param["apiKey"] = BuildConfig.API_KEY
        return api.fetchNewsSources(param)
            .map { data ->
                handleApiSuccess(
                    status = data.status,
                    data = data.sources,
                    totalResults = null
                )
            }.onErrorReturn { e ->
                handleApiError(e)
            }
    }
}