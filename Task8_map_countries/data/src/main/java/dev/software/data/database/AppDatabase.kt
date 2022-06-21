package dev.software.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.software.data.model.CountryEntity


@Database(entities = [CountryEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun DaoDB(): DaoDB
}