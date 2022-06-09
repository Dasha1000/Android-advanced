package dev.software.apicorutineroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.software.apicorutineroom.model.Persons

@Database(entities = [Persons::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun DaoDB(): DaoDB
}