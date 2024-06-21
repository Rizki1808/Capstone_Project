package com.example.capstoneproject.data.tools

import android.util.Log
import com.example.capstoneproject.data.api.ApiConfig
import com.example.capstoneproject.data.api.ApiService
import com.example.capstoneproject.data.api.ApiServiceNews
import com.example.capstoneproject.data.response.DiseasesResponse
import com.example.capstoneproject.data.response.DiseasesDetailResponse
import com.example.capstoneproject.data.response.HistoryResponse
import com.example.capstoneproject.data.response.NewsResponse
import com.example.capstoneproject.data.response.PendeteksiResponse
import com.example.capstoneproject.data.response.PressureResponse
import com.example.capstoneproject.data.response.ProfileResponse
import com.example.capstoneproject.data.response.SugarResponse
import com.example.capstoneproject.data.response.UploadPressureResponse
import com.example.capstoneproject.data.response.UploadSugarResponse
import com.example.capstoneproject.data.response.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import okhttp3.MultipartBody

class UserRepository private constructor(
    private var apiService: ApiService,
    private var apiServiceNews: ApiServiceNews,
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

    suspend fun getDiseaseDetail(id: String): DiseasesDetailResponse {
        return apiService.getDiseasesDetail(id)
    }

    suspend fun getPressure(): PressureResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.getPressure()
    }

    suspend fun postBloodPressure(sistolik: Int, distolik: Int, checkDate: String, checkTime: String): UploadPressureResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.postBloodPressure(sistolik, distolik, checkDate, checkTime)
    }

    suspend fun getSugarBlood(): SugarResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.getSugarBlood()
    }

    suspend fun postSugarBlood(sugar: Int, checkDate: String, checkTime: String): UploadSugarResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.postSugarBlood(sugar, checkDate, checkTime)
    }

    suspend fun getProfile(): ProfileResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.getProfile()
    }

    suspend fun postSkinDetection(image: MultipartBody.Part): PendeteksiResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.postSkinDetection(image)
    }

    suspend fun getSkinDetection(): HistoryResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.getSkinDetection()
    }

    suspend fun postAcneDetection(image: MultipartBody.Part): PendeteksiResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.postAcneDetection(image)
    }

    suspend fun getAcneDetection(): HistoryResponse {
        val session = userPreference.getSession().first()
        Log.d("UserRepository", "Fetching stories with token: ${session.token}")
        val apiServiceWithToken = ApiConfig.getApiService(session.token)
        return apiServiceWithToken.getAcneDetection()
    }

    suspend fun getTopHeadlines(page: Int, pageSize: Int): NewsResponse {
        return apiServiceNews.getTopHeadlines(page, pageSize)
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userPreference: UserPreference, apiService: ApiService, apiServiceNews: ApiServiceNews): UserRepository {
            return instance ?: synchronized(this) {
                UserRepository(apiService, apiServiceNews, userPreference).also { instance = it }
            }
        }
    }
}