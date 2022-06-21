package dev.software.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "countries", indices = [Index(value = ["capital"], unique = true)])
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long=0,
    @ColumnInfo(name = "area")
    val area: Float,
    @ColumnInfo(name = "region")
    val region: String,
    @ColumnInfo(name = "population")
    val population: Float,
    @ColumnInfo(name = "capital")
    val capital: String,
    @ColumnInfo(name = "latitude")
    val latitude: Float,
    @ColumnInfo(name = "longitude")
    val longitude: Float
)