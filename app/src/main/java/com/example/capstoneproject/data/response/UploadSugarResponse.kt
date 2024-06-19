package com.example.capstoneproject.data.response

data class UploadSugarResponse(
	val data: UploadSugar? = null,
	val error: Boolean? = null,
	val message: String? = null
)

data class UploadSugar(
	val checkDate: String? = null,
	val bloodSugar: Int? = null,
	val checkTime: String? = null
)

