package com.example.capstoneproject.data.response

data class UploadPressureResponse(
	val data: UploadPressure? = null,
	val error: Boolean? = null,
	val status: String? = null
)

data class UploadPressure(
	val distolik: Int? = null,
	val checkDate: String? = null,
	val checkTime: String? = null,
	val sistolik: Int? = null
)

