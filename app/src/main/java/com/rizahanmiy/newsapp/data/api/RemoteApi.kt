package com.rizahanmiy.newsapp.data.api

import com.rizahanmiy.newsapp.data.entities.*
import io.reactivex.Observable
import retrofit2.http.*


interface RemoteApi {

    //News return Article {}
    @GET("top-headlines?")
    fun fetchNewsArticles(@QueryMap param: HashMap<String, Any?>): Observable<BaseDataArticlesApi>

//    @GET("everything?")
//    fun fetchNewsEverything(@QueryMap param: HashMap<String, Any?>): Observable<BaseDataArticlesApi>


    //News return Sources {}
    @GET("top-headlines/sources?")
    fun fetchNewsSources(@QueryMap param: HashMap<String, Any?>): Observable<BaseDataSourceApi>

}