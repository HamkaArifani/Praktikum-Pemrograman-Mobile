package com.example.listcompose.Data

import com.example.listcompose.Model.Film
import com.example.listcompose.R

class DataSource {
    fun listFilm(): List<Film> {
        return listOf(
            Film(
                1,
                R.string.title_f01,
                R.string.synopsis_f01,
                R.string.rating,
                R.string.review_f01,
                R.string.score_f01,
                R.drawable.sotl,
                R.string.imdb_f01,
                R.drawable.sotl2
            ),
            Film(
                2,
                R.string.title_f02,
                R.string.synopsis_f02,
                R.string.rating,
                R.string.review_f02,
                R.string.score_f02,
                R.drawable.gitc,
                R.string.imdb_f02,
                R.drawable.gitc2
            ),
            Film(
                3,
                R.string.title_f03,
                R.string.synopsis_f03,
                R.string.rating,
                R.string.review_f03,
                R.string.score_f03,
                R.drawable.phm,
                R.string.imdb_f03,
                R.drawable.phm
            ),
            Film(4,
                R.string.title_f04,
                R.string.synopsis_f04,
                R.string.rating,
                R.string.review_f04,
                R.string.score_f04,
                R.drawable.sktp,
                R.string.imdb_f04,
                R.drawable.sktp2
            ),
            Film(
                5,
                R.string.title_f05,
                R.string.synopsis_f05,
                R.string.rating,
                R.string.review_f05,
                R.string.score_f05,
                R.drawable.wudm,
                R.string.imdb_f05,
                R.drawable.wudm2
            )
        )
    }
}