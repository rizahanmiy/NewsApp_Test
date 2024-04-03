package com.rizahanmiy.newsapp.presentation.di

import android.app.Application
import com.rizahanmiy.newsapp.data.api.RemoteApi
import com.rizahanmiy.newsapp.data.repository.NewsRepositoryImpl
import com.rizahanmiy.newsapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideCartRepository(remoteApi: RemoteApi,aplication: Application): UserRepository {
        return NewsRepositoryImpl(remoteApi,aplication)

    }

}