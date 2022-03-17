package com.unknowncompany.isttestcase.ui.detail.di

import com.unknowncompany.isttestcase.ui.detail.*
import com.unknowncompany.isttestcase.ui.detail.api.DetailApi
import com.unknowncompany.isttestcase.ui.detail.data.DetailRepo
import org.koin.dsl.module
import retrofit2.Retrofit

val detailModule = module {
    fun provideDetailApi(retrofit: Retrofit): DetailApi {
        return retrofit.create(DetailApi::class.java)
    }

    single { provideDetailApi(get()) }
    scope<DetailActivity> {
        scoped<DetailContract.Repo> { DetailRepo(get()) }
        scoped<DetailContract.Interactor> { DetailInteractor(get()) }
        scoped<DetailContract.Router> { DetailRouter(get()) }
        scoped<DetailContract.Presenter> { DetailPresenter(get(), get()) }
    }
}