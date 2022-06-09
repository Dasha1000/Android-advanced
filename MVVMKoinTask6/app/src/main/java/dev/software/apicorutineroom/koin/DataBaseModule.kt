package dev.software.apicorutineroom.koin

import androidx.room.Room
import dev.software.apicorutineroom.database.AppDatabase
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "characters_database"
        ).build()
    }
}