package com.unknowncompany.isttestcase.ui.seeall.di

import com.unknowncompany.isttestcase.ui.seeall.*
import com.unknowncompany.isttestcase.ui.seeall.api.SeeAllApi
import com.unknowncompany.isttestcase.ui.seeall.data.SeeAllRepo
import org.koin.dsl.module
import retrofit2.Retrofit

val seeAllModule = module {
    fun provideSeeAllApi(retrofit: Retrofit): SeeAllApi {
        return retrofit.create(SeeAllApi::class.java)
    }

    single { provideSeeAllApi(get()) }
    scope<SeeAllActivity> {
        scoped<SeeAllContract.Repo> { SeeAllRepo(get()) }
        scoped<SeeAllContract.Interactor> { SeeAllInteractor(get()) }
        scoped<SeeAllContract.Router> { SeeAllRouter(get()) }
        scoped<SeeAllContract.Presenter> { SeeAllPresenter(get(), get()) }
    }
}