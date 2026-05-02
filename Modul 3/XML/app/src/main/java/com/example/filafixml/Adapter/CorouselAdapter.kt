package com.example.filafixml.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filafixml.Model.Film
import com.example.filafixml.databinding.ItemHighlightBinding

class CarouselAdapter(private val onItemClick: (Film)->Unit) :
    ListAdapter<Film, CarouselAdapter.CarouselViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder{
        val binding = ItemHighlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position : Int) {
        holder.bind(getItem(position))
    }
    inner class CarouselViewHolder(private val binding: ItemHighlightBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind (film : Film){
            binding.apply {
                ivHighlight.setImageResource(film.imageId)
                root.setOnClickListener { onItemClick(film) }
            }
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean = oldItem == newItem
    }
}