package com.example.capstoneproject.ui.feature.item.diari.guladarah.tambahgula

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.UploadSugar
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class TambahGulaViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uploadSugar = MutableLiveData<Result<UploadSugar>>()
    val uploadSugar: MutableLiveData<Result<UploadSugar>> get() = _uploadSugar

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    fun uploadSugar(gula: Int, checkDate: String, checkTime: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.postSugarBlood(gula, checkDate, checkTime)
                _uploadSugar.value = Result.success(response.data!!)
            } catch (e: Exception) {
                _uploadSugar.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}