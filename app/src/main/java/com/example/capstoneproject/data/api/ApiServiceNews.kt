package com.example.capstoneproject.data.api

import com.example.capstoneproject.data.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNews {
    @GET("v2/top-headlines?country=us&category=health&apiKey=90a7526947dc4f87b30e797cc07acb9f")
    suspend fun getTopHeadlines(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse
}