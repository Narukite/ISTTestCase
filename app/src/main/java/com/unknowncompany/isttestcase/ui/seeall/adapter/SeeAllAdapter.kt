package com.unknowncompany.isttestcase.ui.seeall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unknowncompany.isttestcase.R
import com.unknowncompany.isttestcase.app.model.Movie
import com.unknowncompany.isttestcase.databinding.ItemMovieBinding

class SeeAllAdapter(private val listener: MovieListener) :
    RecyclerView.Adapter<SeeAllAdapter.ViewHolder>() {

    private val movies: ArrayList<Movie> = arrayListOf()

    fun addData(movies: List<Movie>) {
        val positionStart = this.movies.size
        val itemCount = movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(position: Int) {
            val item = movies[position]
            Glide.with(itemView.context).load(item.posterPath).into(binding.ivMoviePoster)
            binding.tvMovieTitle.text = item.originalTitle
            binding.cl.setOnClickListener { listener.onItemClick(item.id) }
            if (position == movies.lastIndex) listener.onLastItemReach()
        }
    }

    interface MovieListener {
        fun onItemClick(movieId: Int)
        fun onLastItemReach()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = movies.size
}