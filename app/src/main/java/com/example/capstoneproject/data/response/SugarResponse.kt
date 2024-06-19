package com.example.capstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class SugarResponse(
	@SerializedName("data")
	val data: List<DataSugar>,
	@SerializedName("error")
	val error: Boolean? = null,
	@SerializedName("status")
	val status: String? = null
)

data class DataSugar(
	@SerializedName("check_date")
	val checkDate: String? = null,
	@SerializedName("blood_sugar")
	val bloodSugar: Int? = null,
	@SerializedName("check_time")
	val checkTime: String? = null
)

