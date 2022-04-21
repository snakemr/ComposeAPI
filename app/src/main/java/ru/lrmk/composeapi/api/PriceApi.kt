package ru.lrmk.composeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PriceApi {
    @GET("{offset}/{count}")
    suspend fun price(
        @Path("offset") offset: Int = 0,
        @Path("count") count: Int = 10
    ): List<Price>

    companion object {
        fun getAPI(): PriceApi = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/price/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PriceApi::class.java)
    }
}

data class Price(
    val id: Int,
    val name: String,
    val cost: Float
)
