package com.example.filafixml.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.filafixml.data.DataSource
import kotlin.getValue
import com.example.filafixml.R
import com.example.filafixml.databinding.FragmentdetailBinding
import com.example.filafixml.viewmodel.DetailViewModel
import com.example.filafixml.viewmodel.DetailViewModelFactory
import timber.log.Timber

class DetailFragment: Fragment(R.layout.fragmentdetail) {
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel : DetailViewModel by viewModels {
        DetailViewModelFactory(
            DataSource(),
            args.filmId,
            getString(R.string.detailpage)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentdetailBinding.bind(view)

        val film = viewModel.film
        if (film != null){
            Timber.d("Menampilkan Data Detail dari Film: ${getString(film.title)}")
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
            Toast.makeText(requireContext(), viewModel.pageInfo, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), getString(R.string.errormsg), Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
}