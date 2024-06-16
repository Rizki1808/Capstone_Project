package com.example.capstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class DiseasesResponse(

    @field:SerializedName("listStory")
    val listStory: ArrayList<ListStoryItem>,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("story")
    val story: ListStoryItem? = null,

    @field:SerializedName("message")
    val message: String? = null,
)

data class ListStoryItem(

    @field:SerializedName("photoUrl")
    val photoUrl: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("lon")
    val lon: Double,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("lat")
    val lat: Double
)
