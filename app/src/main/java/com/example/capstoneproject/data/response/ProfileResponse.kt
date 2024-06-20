package com.example.capstoneproject.data.response

data class ProfileResponse(
	val data: DataProfile? = null,
	val error: Boolean? = null,
	val status: String? = null
)

data class DataProfile(
	val name: String? = null,
	val email: String? = null
)

