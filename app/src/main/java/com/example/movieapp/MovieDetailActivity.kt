package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.ActivityMovieDetailBinding
import com.example.movieapp.ui.viewmodel.MovieDetailViewModel
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieDetailBinding
    private  val viewModel by viewModels<MovieDetailViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imdbId=intent.getStringExtra("imdbId") as String

        viewModel.getMovieBYId(imdbId)

        viewModel.movieById.observe(this){
            Picasso.get().load(it.Poster).into(binding.poster)
            binding.title.text=it.Title
            binding.year.text=it.Year


        }


    }
}