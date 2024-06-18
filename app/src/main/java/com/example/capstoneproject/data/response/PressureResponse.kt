package com.example.capstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class PressureResponse(
    @SerializedName("data")
    val data: ArrayList<DataBlood>,
    @SerializedName("error")
    val error: Boolean? = null,
    @SerializedName("status")
    val status: String? = null
)

data class DataBlood(
	@SerializedName("distolik")
	val distolik: Int? = null,
	@SerializedName("check_date")
	val checkDate: String? = null,
	@SerializedName("check_time")
	val checkTime: String? = null,
	@SerializedName("sistolik")
	val sistolik: Int? = null
)