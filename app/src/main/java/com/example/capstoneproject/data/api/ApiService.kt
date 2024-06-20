package com.example.capstoneproject.data.api

import com.example.capstoneproject.data.response.DiseasesResponse
import com.example.capstoneproject.data.response.DiseasesDetailResponse
import com.example.capstoneproject.data.response.PendeteksiResponse
import com.example.capstoneproject.data.response.PressureResponse
import com.example.capstoneproject.data.response.ProfileResponse
import com.example.capstoneproject.data.response.SugarResponse
import com.example.capstoneproject.data.response.UploadPressureResponse
import com.example.capstoneproject.data.response.UploadSugarResponse
import com.example.capstoneproject.data.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("full_name") name: String,
        @Field("date_of_birth") dateOfBirth: String,
        @Field("gender") gender: String
    ): Call<UserResponse>

    @GET("diseases")
    suspend fun getDiseases(): DiseasesResponse

    @GET("diseases/{id}")
    suspend fun getDiseasesDetail(
        @Path("id") id: String
    ): DiseasesDetailResponse

    @GET("blood-pressure")
    suspend fun getPressure(): PressureResponse

    @FormUrlEncoded
    @POST("blood-pressure")
    suspend fun postBloodPressure(
        @Field("sistolik") sistolik: Int,
        @Field("distolik") distolik: Int,
        @Field("check_date") checkDate: String,
        @Field("check_time") checkTime: String
    ): UploadPressureResponse

    @GET("profile")
    suspend fun getProfile(): ProfileResponse

    @GET("sugar-blood")
    suspend fun getSugarBlood(): SugarResponse

    @FormUrlEncoded
    @POST("sugar-blood")
    suspend fun postSugarBlood(
        @Field("blood_sugar") gula: Int,
        @Field("check_date") checkDate: String,
        @Field("check_time") checkTime: String
    ): UploadSugarResponse

    @Multipart
    @POST("skin-detection")
    suspend fun postSkinDetection(
        @Part image: MultipartBody.Part
    ): PendeteksiResponse

    @Multipart
    @POST("acne-detection")
    suspend fun postAcneDetection(
        @Part image: MultipartBody.Part
    ): PendeteksiResponse
}