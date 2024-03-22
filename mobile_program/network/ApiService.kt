package com.example.mobileprogram.network

import com.example.mobileprogram.model.ResponseUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    //    get list users with query
    @GET("api/users")
    fun getListUsers(@Query("page") page:String): Call<ResponseUser>

    //    get list user by id using path
    @GET("api/users/{id}")
    fun getUser(@Path("id") id: String): Call<ResponseUser>

    //    post user using field x-www-form-urlencoded
    @FormUrlEncoded
    @POST("api/users")
    fun createUser(
        @Field("name") name: String,
        @Field("job") job: String
    ): Call<ResponseUser>

    //    upload file using multipart
    @Multipart
    @PUT("api/uploadfile")
    fun updateUser(
        @Part("file") file: MultipartBody.Part,
        @PartMap data: Map<String, RequestBody>
    ): Call<ResponseUser>

}