package ru.lrmk.composeapi.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import java.text.SimpleDateFormat
import java.util.*

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

    private val kladr = KladrApi.getAPI()
    suspend fun federalDistricts() = kladr.federalDistricts().mapNotNull { it.federal_district }
    suspend fun regions(federalDistrict: String? = null) = kladr.regions(federalDistrict)
    suspend fun cities(region: Long? = null) = kladr.cities(region)
    suspend fun city(city: Long) = kladr.city(city)

    private val login = LoginAPI.getAPI()
    suspend fun login(name: String, pass: String) = login.login(name, pass)

    companion object {
        fun video(key: String) = "https://mad.lrmk.ru/medialibrary/video/$key.mp4"

        @Composable fun image(poster: String) =
            rememberImagePainter("https://mad.lrmk.ru/medialibrary/small/$poster")

        @Composable fun bigImage(poster: String) =
            rememberImagePainter("https://mad.lrmk.ru/medialibrary/large/$poster")

        @Composable fun icon(weatherIcon: String?) =
            rememberImagePainter(weatherIcon?.let { "https://mad.lrmk.ru/weather/image/$it" })

        @Composable fun flag(countryCode: String) =
            rememberImagePainter("https://mad.lrmk.ru/images/flags/" +
                    countryCode.lowercase() + ".png")

        @Composable
        private fun rememberImagePainter(url: String?) = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build()
        )

        fun time(seconds: Long? = null) = SimpleDateFormat("HH:mm:ss", Locale("RU"))
            .format(Date(seconds?.let{ it * 1000 } ?: Calendar.getInstance().timeInMillis))

        fun year(date: Date) = GregorianCalendar().run {
            time = date
            get(Calendar.YEAR).toString()
        }
    }
}
