package com.example.teravin_testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teravin_testapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieRepository = MovieRepository()

        movieAdapter = MovieAdapter(emptyList())
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = movieAdapter

        loadMovie()
    }

    private fun loadMovie() {
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val movies = movieRepository.fetchMovie()
                withContext(Dispatchers.Main){
                    movieAdapter.updateData(movies)
                }
            } catch (e: MovieRepository.ApiException){
                Log.e("MainActivity", "API Exception: $e")
            } catch (e: Exception){
                Log.e("MainActivity", "API Exception: $e")
            }
        }
    }
}