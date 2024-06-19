package com.example.capstoneproject.ui.feature.item.pendeteksi.pendeteksiwajah

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.PendeteksiResponse
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class WajahViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uploadFace = MutableLiveData<Result<PendeteksiResponse>>()
    val uploadFace : LiveData<Result<PendeteksiResponse>> = _uploadFace

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun uploadFace(image: MultipartBody.Part) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.postAcneDetection(image)
                _uploadFace.value = Result.success(response)
            } catch (e: Exception) {
                Log.e("UploadFace", "Error: ${e.message}")
                _uploadFace.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}