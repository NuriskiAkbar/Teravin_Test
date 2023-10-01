package com.example.teravin_testapp

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MovieRepository {
    private val apiService = ApiService.getInstance().create(ApiInterface::class.java)

     suspend fun fetchMovie(): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<Movie> = apiService.getNowPlayingMovie()
                if (response.isSuccessful) {
                    return@withContext (response.body()?.results ?: emptyList()) as List<Movie>
                } else {
                    // Tangani jika permintaan gagal, misalnya, dengan melempar sebuah exception
                    throw ApiException("Failed to fetch movies")
                }
            } catch (e: Exception) {
                // Tangani kesalahan jaringan atau lainnya
                throw e
            }
        }
    }

    class ApiException(s: String) : IOException(s)
}