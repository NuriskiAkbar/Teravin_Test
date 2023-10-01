package com.example.teravin_testapp

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.teravin_testapp.databinding.ItemMovieBinding

class MovieAdapter(private var movieList: List<Movie>):
RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(movie: Movie) {
            binding.textView.text = movie.results!![0]!!.originalTitle
            binding.textView2.text = movie.results[0]!!.releaseDate
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(movieList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newMovies: List<Movie>) {
        movieList = newMovies
        notifyDataSetChanged()
    }
}