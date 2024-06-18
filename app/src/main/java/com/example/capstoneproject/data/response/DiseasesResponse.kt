package com.example.capstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class DiseasesResponse(
	@SerializedName("data")
	val data: ArrayList<DataItem>,
	@SerializedName("diseases")
	val diseases: DataItem? = null,
	@SerializedName("error")
	val error: Boolean? = null,
	@SerializedName("status")
	val status: String? = null
)

data class DataItem(
	@SerializedName("id")
	val id: String? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("description")
	val description: String? = null,
	@SerializedName("prevention")
	val prevention: String? = null,
	@SerializedName("treatment")
	val treatment: String? = null,
	@SerializedName("updatedAt")
	val updatedAt: String? = null,
	@SerializedName("imageURL")
	val imageURL: String? = null
)
