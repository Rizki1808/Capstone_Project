package com.example.capstoneproject.ui.feature.item.pendeteksikulit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.KulitResponse
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class KulitViewModel(private val repository: UserRepository) : ViewModel()  {

    private val _uploadSkin = MutableLiveData<Result<KulitResponse>>()
    val uploadSkin: LiveData<Result<KulitResponse>> = _uploadSkin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun uploadSkin(image: MultipartBody.Part) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.postSkinDetection(image)
                _uploadSkin.value = Result.success(response)
            } catch (e: Exception) {
                Log.e("UploadSkin", "Error: ${e.message}")
                _uploadSkin.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}