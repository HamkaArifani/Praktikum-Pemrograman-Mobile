package com.example.filafixml.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filafixml.Model.Film
import com.example.filafixml.databinding.ItemFilmBinding

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

                root.setOnClickListener { onDetailsClick(film) }
                btDetails.setOnClickListener { onDetailsClick(film) }
                btIMDB.setOnClickListener { onImdbClick(film) }
            }
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem == newItem
    }
}