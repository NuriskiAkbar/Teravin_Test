package com.example.teravin_testapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    val movieList = MutableLiveData<List<ResultsItem>>()

    fun loadMovies(){
        viewModelScope.launch {
            try {
                val movies = MovieRepository().fetchMovie()
                movieList.postValue(movies)
            } catch (e: MovieRepository.ApiException) {
                // Handle API error
            } catch (e: Exception) {
                // Handle other errors
            }
        }
    }
}