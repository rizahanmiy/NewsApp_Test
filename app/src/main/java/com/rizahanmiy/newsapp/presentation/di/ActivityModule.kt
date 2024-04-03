package com.rizahanmiy.newsapp.presentation.di

import com.rizahanmiy.newsapp.presentation.ui.main.MainActivity
import com.rizahanmiy.newsapp.presentation.ui.splash.SplashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun splashScreen(): SplashScreen

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}