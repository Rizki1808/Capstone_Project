package com.example.capstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(
	@SerializedName("data")
	val data: ArrayList<DataHistory>,
	@SerializedName("error")
	val error: Boolean? = null,
	@SerializedName("status")
	val status: String? = null
)

data class DataHistory(
	@SerializedName("result")
	val result: String? = null,
	@SerializedName("image")
	val image: String? = null,
	@SerializedName("createdAt")
	val createdAt: String? = null,
	@SerializedName("id")
	val id: String? = null
)

