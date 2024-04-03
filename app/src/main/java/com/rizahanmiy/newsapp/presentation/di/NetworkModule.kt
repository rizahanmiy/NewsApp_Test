package com.rizahanmiy.newsapp.presentation.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.rizahanmiy.newsapp.BuildConfig
import com.rizahanmiy.newsapp.data.api.RemoteApi
import com.rizahanmiy.newsapp.utils.common.hasNetwork
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)

    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(
        cache: Cache,
        application: Application,
    ): OkHttpClient {
        val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
            ).build()

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectionSpecs(Collections.singletonList(spec))
        httpClientBuilder.cache(cache)
        httpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
        httpClientBuilder.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .build()
            chain.proceed(newRequest)
        }

        //SSL Security

        httpClientBuilder.addInterceptor { chain ->
            var request = chain.request()

            request = if (hasNetwork(application))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5)
                    .header("Content-Type", "application/json")
                    .build()
            else
                request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 2)
                    .build()

            chain.proceed(request)


        }

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logging)
//            httpClientBuilder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
        }
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()

    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }

}