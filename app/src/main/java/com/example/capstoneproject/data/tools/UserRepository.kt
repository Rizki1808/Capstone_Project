package com.example.capstoneproject.data.tools

import com.example.capstoneproject.data.api.ApiService
import com.example.capstoneproject.data.response.DiseasesResponse
import com.example.capstoneproject.data.response.UserModel
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private var apiService: ApiService,
    private val userPreference: UserPreference
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun getDiseases(): DiseasesResponse {
        return apiService.getDiseases()
    }

    suspend fun getDiseaseDetail(id: String): DiseasesResponse {
        return apiService.getDiseasesDetail(id)
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userPreference: UserPreference, apiService: ApiService): UserRepository {
            return instance ?: synchronized(this) {
                UserRepository(apiService, userPreference).also { instance = it }
            }
        }
    }
}