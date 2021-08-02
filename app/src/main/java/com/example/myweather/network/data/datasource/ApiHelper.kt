package com.example.movieapp.data.datasource

import com.example.movieapp.api.ApiService
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {

    val apiKey: String = "lkkjhgghjj"

    suspend fun getMovies() = apiService.getMovieList(
        apiKey,
        1
    )

    suspend fun getMoviesGenre() = apiService.getMovieGenreList(
        apiKey,
        "en-US"
    )

    suspend fun getTVGenre() = apiService.getTVListGenreList(
        apiKey,
        "en-US"
    )

    suspend fun getMovieRecommendations(movieID: Int, pageNo: Int) = apiService.getRecommendedMovieList(
        movieID,
        apiKey,
        "en-US",
        pageNo
    )

    suspend fun getLanguageList() = apiService.getLanguageList(
        apiKey
    )

    suspend fun getMoviesByGenre(genre_id: Int) = apiService.getMoviesListByGenre(
        apiKey,
        genre_id
    )
}