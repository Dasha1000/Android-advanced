package dev.software.data.koin

import androidx.room.Room
import dev.software.data.database.AppDatabase

import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "persons_database"
        ).build()
    }

    single {get<AppDatabase>().DaoDB()}
}