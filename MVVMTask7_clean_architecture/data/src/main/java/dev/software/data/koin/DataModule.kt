package dev.software.data.koin

import org.koin.dsl.module


val dataModule = module {
    includes(
        networkTVModule,
        dataBaseModule,
        tvRepositoryModule,
        useCaseModule
    )
}