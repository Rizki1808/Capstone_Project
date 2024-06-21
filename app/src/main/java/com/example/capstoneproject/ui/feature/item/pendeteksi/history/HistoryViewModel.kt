package com.example.capstoneproject.ui.feature.item.pendeteksi.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.HistoryResponse
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: UserRepository) : ViewModel() {

    private val _getSkin = MutableLiveData<Result<HistoryResponse>>()
    val getSkin: LiveData<Result<HistoryResponse>> = _getSkin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getSkinDetection() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = repository.getSkinDetection()
                _getSkin.value = Result.success(result)
                println("Skin Detail Loaded: $_getSkin.value")
            } catch (e: Exception) {
                _getSkin.value = Result.failure(e)
                println("Error Loading Skin Detail: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAcneDetection() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = repository.getAcneDetection()
                _getSkin.value = Result.success(result)
                println("Face Detail Loaded: $_getSkin.value")
            } catch (e: Exception) {
                _getSkin.value = Result.failure(e)
                println("Error Loading Face Detail: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}