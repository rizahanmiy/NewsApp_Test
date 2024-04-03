package com.rizahanmiy.newsapp.presentation.di

import com.rizahanmiy.newsapp.presentation.ui.explore.ExploreFragment
import com.rizahanmiy.newsapp.presentation.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun exploreFragment(): ExploreFragment

}