package ru.lrmk.composeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface LoginAPI {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("name") name: String,
        @Field("pass") pass: String
    ) : Login

    @POST("reg")
    suspend fun registration(
        @Body register: Register
    ) : Registration

    @GET("info")
    suspend fun info(
        @Query("token") token: Int
    ) : UserInfo

    @GET("files/{user}")
    suspend fun files(
        @Path("user") user: String
    ) : UserFiles

    companion object {
        fun getAPI(): LoginAPI = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LoginAPI::class.java)
    }
}

class Login(
    val token: Int,
    val error: String?
)

class Register(
    val name: String,
    val pass: String,
    val mail: String
)

class Registration(
    val id: Int,
    val error: String?
)

class UserInfo(
    val id: Int,
    val name: String,
    val pass: String,
    val mail: String,
    val date: String,
    val error: String?
)

class UserFiles(
    val user: String,
    val files: List<File>
) {
    class File(
        val name: String,
        val size: Int
    )
}