package com.example.capstoneproject.data.response

data class DiseasesDetailResponse(
	val data: DataDetail? = null,
	val error: Boolean? = null,
	val message: String? = null
)

data class DataDetail(
	val image: String? = null,
	val treatment: String? = null,
	val createdAt: String? = null,
	val name: String? = null,
	val description: String? = null,
	val id: Int? = null,
	val prevention: String? = null,
	val updatedAt: String? = null
)

