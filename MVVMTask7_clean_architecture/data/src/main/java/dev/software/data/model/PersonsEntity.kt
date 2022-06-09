package dev.software.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "original")
    val original: String?
)