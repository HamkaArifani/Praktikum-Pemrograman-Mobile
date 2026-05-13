package com.example.filafixml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filafixml.model.Film
import com.example.filafixml.databinding.ItemFilmBinding
import timber.log.Timber

class MovieListAdapter(
    private val onImdbClick: (Film) -> Unit,
    private val onDetailsClick: (Film)->Unit):
    ListAdapter<Film, MovieListAdapter.MovieViewHolder>(DiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position : Int){
        val film = getItem(position)
        holder.bind(film)
    }

    inner class MovieViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.apply {
                tvTitleFilm.text = root.context.getString(film.title)
                tvRatingFilm.text = root.context.getString(film.rating)
                ivImageFilm.setImageResource(film.imageId)
                tvDescFilm.text= root.context.getString(film.synopsis)
                tvScoreFilm.text=root.context.getString(film.score)

                root.setOnClickListener {
                    Timber.d("Item List dari ${root.context.getString(film.title)} Ditekan")
                    onDetailsClick(film)
                }
                btDetails.setOnClickListener {
                    Timber.d("Tombol Detail dari Film: ${root.context.getString(film.title)} Ditekan")
                    onDetailsClick(film)
                }
                btIMDB.setOnClickListener {
                    Timber.d("Tombol IMDB dari Film : ${root.context.getString(film.title)} Ditekan")
                    onImdbClick(film)
                }
            }
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem == newItem
    }
}