package dev.software.apicorutineroom

import android.app.Application
import dev.software.apicorutineroom.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class TVApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TVApplication)
            modules(
                tvRepositoryModule,
                dataBaseModule,
                networkTVModule,
                viewModelModule
            )
        }
    }
}