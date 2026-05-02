package com.example.filafixml.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.filafixml.R
import androidx.navigation.fragment.findNavController
import com.example.filafixml.Adapter.CarouselAdapter
import com.example.filafixml.Adapter.MovieListAdapter
import com.example.filafixml.ViewModel.FilmViewModel
import com.example.filafixml.databinding.FragmenthomeBinding
import kotlin.getValue

class HomeFragment : Fragment(R.layout.fragmenthome){
    private val viewModel : FilmViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmenthomeBinding.bind(view)

        val carouselAdapter = CarouselAdapter { film ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(film.id)
            findNavController().navigate(action)
        }

        val movieListAdapter = MovieListAdapter(
            onDetailsClick = { film ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(film.id)
            findNavController().navigate(action)
        }, onImdbClick = { film ->
            val url = getString(film.imdbUrl)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        )

        binding.rvCarousel.apply {
            adapter = carouselAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext(), androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        }
        binding.btnLanguage.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLanguageFragment()
            findNavController().navigate(action)
        }
        viewModel.highlightList.observe(viewLifecycleOwner) { films ->
            carouselAdapter.submitList(films)
        }

        viewModel.movieList.observe(viewLifecycleOwner) { films ->
            movieListAdapter.submitList(films)
        }

    }
}