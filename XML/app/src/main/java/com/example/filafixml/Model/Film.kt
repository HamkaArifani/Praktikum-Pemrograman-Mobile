package com.example.filafixml.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film (
    val id: Int,
    @StringRes val title: Int,
    @StringRes val synopsis: Int,
    @StringRes val rating: Int,
    @StringRes val review: Int,
    @StringRes val score: Int,
    @DrawableRes val imageId: Int,
    @StringRes val imdbUrl: Int,
    @DrawableRes val bigImageId: Int
)