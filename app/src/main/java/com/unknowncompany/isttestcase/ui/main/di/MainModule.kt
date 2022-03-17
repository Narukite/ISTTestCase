package com.unknowncompany.isttestcase.ui.main.di

import com.unknowncompany.isttestcase.ui.main.*
import com.unknowncompany.isttestcase.ui.main.api.MainApi
import com.unknowncompany.isttestcase.ui.main.data.MainRepo
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    single { provideMainApi(get()) }
    scope<MainActivity> {
        scoped<MainContract.Repo> { MainRepo(get()) }
        scoped<MainContract.Interactor> { MainInteractor(get()) }
        scoped<MainContract.Router> { MainRouter(get()) }
        scoped<MainContract.Presenter> { MainPresenter(get(), get()) }
    }
}
