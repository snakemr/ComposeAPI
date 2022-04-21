package ru.lrmk.composeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

internal interface MediaAPI {
    @GET("genre/movie/list")
    suspend fun genres(): Genres

    @GET("discover/movie")
    suspend fun movies(
        @Query("with_genres") with_genres: String = "",
        @Query("page") page: Int = 1,
    ): Movies

    @GET("search/movie")
    suspend fun search(
        @Query("query") query: String
    ): Movies

    @GET("movie/{id}")
    suspend fun movie(
        @Path("id") id: Int,
        @Query("append_to_response") append: String = "videos"
    ): Movie

    companion object {
        fun getAPI(): MediaAPI = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/medialibrary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MediaAPI::class.java)
    }
}

internal class Genres(
    val genres: List<Genre> = listOf()
)

data class Genre(
    val id: Int,
    val name: String
)

class Movies(
    val page: Int = 0,
    val total_pages: Int = 0,
    val total_results: Int = 0,
    val results: List<Movie> = listOf()
)

data class Movie(
    val id: Int,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val genres: List<Genre>,
    val production_countries: List<Country>,
    val production_companies: List<Company>,
    val release_date: Date,
    val videos: Videos
)

data class Country(
    val iso_3166_1: String,
    val name: String
)

data class Company(
    val logo_path: String,
    val name: String
)

class Videos(
    val results: List<Video> = listOf()
) {
    data class Video(
        val key: String,
        val name: String,
        val site: String
    )
}