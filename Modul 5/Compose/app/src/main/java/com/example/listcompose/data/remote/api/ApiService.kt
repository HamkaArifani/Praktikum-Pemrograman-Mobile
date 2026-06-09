package com.example.listcompose.data.remote.api

import com.example.listcompose.data.remote.dto.FilmDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail (
        @Path("movie_id") movieId : Int,
        @Query("language") language: String
    ): Response<FilmDto>
}