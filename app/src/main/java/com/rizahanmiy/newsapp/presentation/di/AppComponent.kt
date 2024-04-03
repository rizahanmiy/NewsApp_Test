package com.rizahanmiy.newsapp.presentation.di


import android.app.Application
import com.rizahanmiy.newsapp.data.base.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class,
        WorkerModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApplication: BaseApplication)

}