package ru.lrmk.composeapi.api

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

internal interface KladrApi {
    @GET("fdistrict")
    suspend fun federalDistricts(): List<FederalDistrict>

    @GET("region")
    suspend fun regions(
        @Query("fdistrict") federalDistrict: String? = null,
        @Query("federal") showFederalDistrict: Int? = 1,
        @Query("timezone") showTimeZone: Int? = 1,
        @Query("postal") showPostal: Int? = 1
    ): List<Region>

    @GET("city")
    suspend fun cities(@Query("region_id") region: Long? = null): List<City>

    @GET("city")
    suspend fun city(@Query("city_id") city: Long,
             @Query("address") showAddress: Int? = 1,
             @Query("postal") showPostal: Int? = 1,
             @Query("geo") showGeo: Int? = 1,
             @Query("population") showPopulation: Int? = 1,
             @Query("foundation") showFoundation: Int? = 1
    ): List<City>

    companion object {
        fun getAPI(): KladrApi = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/kladr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(KladrApi::class.java)
    }
}

data class FederalDistrict(val federal_district: String?)

data class Region(
    @SerializedName("kladr_id")
    val id: Long,
    @SerializedName("name_with_type")
    val name: String,
    @SerializedName("federal_district")
    val federalDistrict: String,
    @SerializedName("timezone")
    val timeZone: String,
    @SerializedName("postal_code")
    val postalCode: Int
)


data class City (
    @SerializedName("kladr_id")
    val id: Long,
    val type: String,
    val city: String,
    @SerializedName("postal_code")
    val postalCode: Int,
    @SerializedName("geo_lat")
    val geoLat: Double,
    @SerializedName("geo_lon")
    val geoLon: Double,
    @SerializedName("foundation_year")
    val foundation: String,
    val population: Int,
    val address: String
)
