package com.example.capstoneproject.data.api

import com.example.capstoneproject.data.response.DiseasesResponse
import com.example.capstoneproject.data.response.DiseasesDetailResponse
import com.example.capstoneproject.data.response.NewsResponse
import com.example.capstoneproject.data.response.PendeteksiResponse
import com.example.capstoneproject.data.response.PressureResponse
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

interface ApiServiceNews {
    @GET("v2/top-headlines?country=id&category=health&apiKey=90a7526947dc4f87b30e797cc07acb9f")
    suspend fun getTopHeadlines(): NewsResponse
}