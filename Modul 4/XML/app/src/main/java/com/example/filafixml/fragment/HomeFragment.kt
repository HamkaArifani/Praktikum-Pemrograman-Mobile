package com.example.filafixml.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.filafixml.R
import androidx.navigation.fragment.findNavController
import com.example.filafixml.adapter.CarouselAdapter
import com.example.filafixml.adapter.MovieListAdapter
import com.example.filafixml.data.DataSource
import com.example.filafixml.viewmodel.FilmViewModel
import com.example.filafixml.databinding.FragmenthomeBinding
import com.example.filafixml.viewmodel.FilmViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.getValue

class HomeFragment : Fragment(R.layout.fragmenthome){
    private val viewModel : FilmViewModel by viewModels {
        FilmViewModelFactory(DataSource(), getString(R.string.homepage))
    }

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.highlightList.collect { films ->
                        carouselAdapter.submitList(films)
                    }
                }
                launch {
                    viewModel.movieList.collect { films ->
                        if(films.isNotEmpty()){
                            Timber.d("Item Film yang Dikirimkan pada List: ${films.size}")
                            movieListAdapter.submitList(films)
                        }
                    }
                }
            }
        }

        Toast.makeText(requireContext(), getString(R.string.homepage), Toast.LENGTH_SHORT).show()

    }
}