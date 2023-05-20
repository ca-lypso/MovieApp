package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MovieResponseById
import com.example.movieapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(val repo: Repository) :ViewModel() {
    val movieById= MutableLiveData<MovieResponseById>()

    fun getMovieBYId(imdbId:String){
        viewModelScope.launch (Dispatchers.IO){
            val response=repo.getMoviesById(imdbId)
            if (response.isSuccessful&&response.code()==200){
                val body=response.body()
                movieById.postValue(body)
            }
        }
    }
}