package com.example.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieCardViewBinding
import com.example.movieapp.model.Search
import com.squareup.picasso.Picasso

class MovieAdapter(val movieClickListener:MovieClickListener,private var movieList: List<Search>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(val view: MovieCardViewBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = MovieCardViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun update(newList: List<Search>) {
        this.movieList = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        val b = holder.view


        Picasso.get().load(movie.Poster).into(b.poster)
        b.title.text=movie.Title

        b.poster.setOnClickListener {

            movieClickListener.movieClickListener(movie.imdbID)
        }

    }

}

interface MovieClickListener {
    fun movieClickListener(imdbID: String)
}