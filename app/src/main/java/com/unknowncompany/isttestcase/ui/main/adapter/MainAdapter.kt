package com.unknowncompany.isttestcase.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unknowncompany.isttestcase.R
import com.unknowncompany.isttestcase.app.model.MainMovie
import com.unknowncompany.isttestcase.databinding.ItemMovieBinding

class MainAdapter(private val movies: List<MainMovie>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(position: Int) {
            val item = movies[position]
            Glide.with(itemView.context).load(item.posterPath).into(binding.ivMoviePoster)
            binding.tvMovieTitle.text = item.originalTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = movies.size
}