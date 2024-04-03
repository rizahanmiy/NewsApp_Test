package com.rizahanmiy.newsapp.presentation.di


import androidx.lifecycle.ViewModelProvider
import com.rizahanmiy.newsapp.presentation.viewmodel.BaseViewModel
import com.rizahanmiy.newsapp.presentation.viewmodel.NewsViewModel
import com.rizahanmiy.newsapp.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel) : BaseViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

}