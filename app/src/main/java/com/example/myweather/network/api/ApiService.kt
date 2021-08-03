package com.example.movieapp.api

import com.example.myweather.models.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") api_key: String,
        @Query("q") city: String,
        @Query("aqi") aqi: String
    ) : CurrentWeather

    @GET("genre/movie/list")
    suspend fun getMovieGenreList(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    )

    @GET("genre/tv/list")
    suspend fun getTVListGenreList(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    )

    @GET("movie/{movieID}/recommendations")
    suspend fun getRecommendedMovieList(
        @Path("movieID") movieID: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") pageNo: Int
    )

    @GET("configuration/languages")
    suspend fun getLanguageList(
        @Query("api_key") api_key: String
    )

    @GET("discover/movie")
    suspend fun getMoviesListByGenre(
        @Query("api_key") api_key: String,
        @Query("with_genres") genre_id: Int
    )

}