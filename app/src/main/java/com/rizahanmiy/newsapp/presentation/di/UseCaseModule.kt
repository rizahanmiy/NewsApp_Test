package com.rizahanmiy.newsapp.presentation.di

import com.rizahanmiy.newsapp.domain.repository.UserRepository
import com.rizahanmiy.newsapp.domain.usecase.UserUseCase
import com.rizahanmiy.newsapp.utils.common.AsyncObservableTransformer
import dagger.Module
import dagger.Provides


@Module
class UseCaseModule {

    @Provides
    internal fun provideLoginUseCase(repository: UserRepository): UserUseCase{
        return UserUseCase(
            AsyncObservableTransformer(),
            AsyncObservableTransformer(),
            AsyncObservableTransformer(),
            repository)

    }
}