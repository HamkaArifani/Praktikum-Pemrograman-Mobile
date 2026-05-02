package com.example.filafixml.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filafixml.Data.DataSource
import kotlin.getValue
import com.example.filafixml.R
import com.example.filafixml.databinding.FragmentdetailBinding

class DetailFragment: Fragment(R.layout.fragmentdetail) {
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentdetailBinding.bind(view)

        val filmId = args.filmId
        val film = DataSource().listFilm().find { it.id== filmId }
            ?: throw IllegalArgumentException(getString(R.string.errormsg))

        binding.apply {
            tvFilmTitle.text=getString(film.title)
            tvFilmSynopsis.text=getString((film.synopsis))
            tvFilmRating.text=getString(film.rating)
            tvfilmScore.text=getString(film.score)
            tvFilmReview.text=getString(R.string.review)
            tvFilmComment.text=getString(film.review)
            ivPoster.setImageResource(film.imageId)
            headerBackDetail.btnBack.setOnClickListener { findNavController().navigateUp()}
            ivBigImage.setImageResource(film.bigImageId)
        }
    }
}