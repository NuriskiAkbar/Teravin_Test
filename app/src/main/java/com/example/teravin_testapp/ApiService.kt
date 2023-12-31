package com.example.teravin_testapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/"
        private var retrofit: Retrofit? = null
        fun getInstance() : Retrofit{

            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}