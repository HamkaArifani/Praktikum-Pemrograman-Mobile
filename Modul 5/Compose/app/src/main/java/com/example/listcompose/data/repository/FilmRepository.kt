package com.example.listcompose.data.repository

import coil.network.HttpException
import com.example.listcompose.data.local.pref.SettingPreferences
import com.example.listcompose.data.local.room.FilmDao
import com.example.listcompose.data.local.room.FilmEntity
import com.example.listcompose.data.remote.api.ApiService
import com.example.listcompose.data.remote.response.ErrorStatus
import com.example.listcompose.domain.model.Film
import com.example.listcompose.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.IOException
import timber.log.Timber

class FilmRepository(
    private val apiService: ApiService,
    private val filmDao: FilmDao,
    private val settingPreferences: SettingPreferences
) {
    fun getAllMovies(): Flow<List<Film>>{
        return filmDao.getAllMovies().map { entityList->
            entityList.map { entity -> entity.toDomain() }
        }
    }

    fun getMovieById(movieId: Int): Flow<Film> {
        return filmDao.getMovieById(movieId).map { entity ->
            entity.toDomain()
        }
    }

    suspend fun refreshMovies(): ErrorStatus? {
        return try{
            val activeLanguage = settingPreferences.getLanguageSetting.first()
            val filmIds = listOf(274, 1393326, 687163, 1310741, 812583)
            val filmEntities = mutableListOf<FilmEntity>()

            for (id in filmIds) {
                val response = apiService.getMovieDetail(movieId = id, language = activeLanguage)

                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        filmEntities.add(
                            FilmEntity(
                                id = responseBody.id,
                                title = responseBody.title,
                                posterPath = responseBody.posterPath,
                                releaseDate = responseBody.releaseDate
                            )
                        )
                    }
                }
            }

            if(filmEntities.isNotEmpty()){
                filmDao.clearAllMovies()
                filmDao.insertMovies(filmEntities)
                null
            }else{
                ErrorStatus.SERVER_ERROR
            }
        }catch (e: IOException) {
            ErrorStatus.NO_INTERNET
        } catch (e: HttpException) {
            ErrorStatus.SERVER_ERROR
        } catch (e: Exception) {
            Timber.e(e, "Penyebab Error:")
            ErrorStatus.UNKNOWN_ERROR
        }
    }
}