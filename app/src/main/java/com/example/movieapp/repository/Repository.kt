package com.example.movieapp.repository

import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.MovieResponseById
import com.example.movieapp.model.Search
import com.example.movieapp.retrofit.ApiUtils
import com.example.movieapp.retrofit.WebApiService
import retrofit2.Response

class Repository(  val apiservis :WebApiService) {

  suspend fun getMovie(search: String): Response<MovieResponse> {
      return apiservis.getMovies(search)
  }
   suspend fun getMoviesById(imdb:String):Response<MovieResponseById>{
       return apiservis.getMoviesById(imdbId = imdb)
   }

}