package ru.lrmk.composeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

internal interface KinopoiskAPI {
    @GET("filters")
    suspend fun filters(): Filters

    @GET(".")
    suspend fun films(): Films

    companion object {
        fun getAPI(): KinopoiskAPI = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/kinopoisk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(KinopoiskAPI::class.java)
    }
}


data class Filters(
    val genres: List<Genre> = listOf(),
    val countries: List<Country> = listOf()
) {
    data class Genre(val genre: String, val id: Int = 0)
    data class Country(val country: String, val id: Int = 0)
}

data class Film(
    val kinopoiskId: Int,
    val imdbId: String,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Float,
    val ratingImdb: Float,
    val year: Int,
    val type: String,
    val posterUrl : String,
    val posterUrlPreview : String
)

data class Films(
    val total: Int = 0,
    val totalPages: Int = 0,
    val items: List<Film> = listOf()
)
