package com.example.listcompose.data.local.room

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<FilmEntity>>
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<FilmEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<FilmEntity>): List<Long>
    @Query("DELETE FROM movies")
    suspend fun clearAllMovies(): Int
}