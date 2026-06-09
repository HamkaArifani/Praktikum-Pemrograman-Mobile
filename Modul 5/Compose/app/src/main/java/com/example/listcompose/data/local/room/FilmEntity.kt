package com.example.listcompose.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class FilmEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="poster_path") val posterPath: String?,
    @ColumnInfo(name="release_date") val releaseDate: String?
)