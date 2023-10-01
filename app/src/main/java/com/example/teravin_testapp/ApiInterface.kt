package com.example.teravin_testapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/3/movie/now_playing?api_key=4354fd221c039cfb3bdf5e16892695d0")
    suspend fun getNowPlayingMovie(): Response<Movie>
}