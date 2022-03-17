package com.unknowncompany.isttestcase.app

import android.app.Application
import com.unknowncompany.isttestcase.app.di.networkModule
import com.unknowncompany.isttestcase.ui.main.di.mainModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(networkModule, mainModule))
        }
    }

}