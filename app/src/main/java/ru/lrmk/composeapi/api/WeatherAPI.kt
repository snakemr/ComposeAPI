package ru.lrmk.composeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherAPI {
    @GET("weather")
    suspend fun weather(
        @Query("q") query: String
    ): Weather

    @GET("weather")
    suspend fun weather(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): Weather

    companion object {
        fun getAPI(): WeatherAPI = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherAPI::class.java)
    }
}

class Weather(
    val base: String = "",
    val clouds: Clouds = Clouds(0),
    val cod: Int = 0,
    val coord: Coord = Coord(0.0,0.0),
    val dt: Long = 0L,
    val id: Int = 0,
    val main: Main = Main(0.0, 0,0, 0.0, 0.0, 0.0),
    val name: String = "",
    val rain: Rain = Rain(0.0),
    val sys: Sys = Sys("", 0, 0L, 0L, 0),
    val timezone: Int = 0,
    val visibility: Int = 0,
    val weather: List<Weather> = listOf(),
    val wind: Wind = Wind(0,0.0)
) {
    class Clouds(
        val all: Int
    )

    class Coord(
        val lat: Double,
        val lon: Double
    )

    class Main(
        val feels_like: Double,
        val humidity: Int,
        val pressure: Int,
        val temp: Double,
        val temp_max: Double,
        val temp_min: Double
    )

    class Rain(
        val `1h`: Double
    )

    class Sys(
        val country: String,
        val id: Int,
        val sunrise: Long,
        val sunset: Long,
        val type: Int
    )

    class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    )

    class Wind(
        val deg: Int,
        val speed: Double
    )
}