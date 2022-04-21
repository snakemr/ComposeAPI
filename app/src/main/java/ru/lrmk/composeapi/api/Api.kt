package ru.lrmk.composeapi.api

import androidx.compose.runtime.Composable
import coil.compose.rememberImagePainter

class Api {
    private val cinema = MediaAPI.getAPI()
    suspend fun genres() = cinema.genres().genres
    suspend fun movies(with_genres: String = "", page: Int = 1) = cinema.movies(with_genres, page)
    suspend fun search(query: String) = cinema.search(query)
    suspend fun movie(id: Int) = cinema.movie(id)

    private val price = PriceApi.getAPI()
    suspend fun price(offset: Int = 0, count: Int = 10) = price.price(offset, count)

    private val kinopoisk = KinopoiskAPI.getAPI()
    suspend fun filters() = kinopoisk.filters()
    suspend fun films() = kinopoisk.films()

    private val weather = WeatherAPI.getAPI()
    suspend fun weather(query: String) = weather.weather(query)
    suspend fun weather(lat: Float, lon: Float) = weather.weather(lat, lon)

    companion object {
        @Composable fun image(poster: String) =
            rememberImagePainter("https://mad.lrmk.ru/medialibrary/small/$poster")

        @Composable fun bigImage(poster: String) =
            rememberImagePainter("https://mad.lrmk.ru/medialibrary/image/$poster")

        @Composable fun icon(weather: String) =
            rememberImagePainter("https://mad.lrmk.ru/weather/image/$weather")
    }
}
