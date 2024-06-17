@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.capstoneproject.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.capstoneproject.data.api.ApiConfig
import com.example.capstoneproject.data.api.ApiService
import com.example.capstoneproject.data.response.ListStoryItem
import com.example.capstoneproject.data.response.UserModel
import com.example.capstoneproject.ui.feature.item.infopenyakit.InfoPenyakitPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

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

    fun getStoriesPaging(): Flow<PagingData<ListStoryItem>> {
        return userPreference.getSession().flatMapLatest { session ->
            val apiServiceWithToken = ApiConfig.getApiService(session.token)
            Pager(
                config = PagingConfig(
                    pageSize = 5
                ),
                pagingSourceFactory = {
                    InfoPenyakitPagingSource(apiServiceWithToken, session.token)
                }
            ).flow
        }
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