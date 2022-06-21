package dev.software.data.koin

import androidx.room.Room
import dev.software.data.database.AppDatabase

import org.koin.dsl.module

internal val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "countries_database"
        ).build()
    }

    single {get<AppDatabase>().DaoDB()}
}