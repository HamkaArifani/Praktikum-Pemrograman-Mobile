package com.example.listcompose.domain.model

import com.example.listcompose.data.local.room.FilmEntity

data class Film (
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?
)

fun FilmEntity.toDomain(): Film{
    return Film(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate
    )
}