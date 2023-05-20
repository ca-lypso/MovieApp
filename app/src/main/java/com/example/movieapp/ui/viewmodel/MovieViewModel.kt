package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MovieResponseById
import com.example.movieapp.model.Search
import com.example.movieapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val repo:Repository) : ViewModel() {

    val moviewList = MutableLiveData<List<Search>>()

    fun getMovie(search: String) {
        val job = viewModelScope.launch(IO) {
            val response = repo.getMovie(search)
            if (response.isSuccessful) {
                val body = response.body()
                moviewList.postValue(body?.Search)
            }
        }
    }



}