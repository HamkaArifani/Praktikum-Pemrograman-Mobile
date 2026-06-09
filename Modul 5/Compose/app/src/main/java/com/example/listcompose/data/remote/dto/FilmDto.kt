package com.example.listcompose.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto (
    @SerialName("id")
    val id : Int,
    @SerialName("title")
    val title : String,
    @SerialName("poster_path")
    val posterPath : String?,
    @SerialName("release_date")
    val releaseDate : String?
)