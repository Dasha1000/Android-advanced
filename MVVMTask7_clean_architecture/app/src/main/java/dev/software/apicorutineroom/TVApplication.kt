package dev.software.apicorutineroom

import android.app.Application
import dev.software.apicorutineroom.koin.*
import dev.software.data.koin.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class TVApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TVApplication)
            modules(
                dataModule,
                viewModelModule

            )
        }
    }
}