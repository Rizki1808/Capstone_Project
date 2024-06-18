package com.example.capstoneproject.ui.feature.item.diari.tekanandarah.tambahtekanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.UploadPressure
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class TambahTekananViewModel(private val repository: UserRepository) : ViewModel() {

    private val _uploadPressure = MutableLiveData<Result<UploadPressure>>()
    val uploadPressure: LiveData<Result<UploadPressure>> = _uploadPressure

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun uploadPressure(sistolik: Int, distolik: Int, checkDate: String, checkTime: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.postBloodPressure(sistolik, distolik, checkDate, checkTime)
                _uploadPressure.value = Result.success(response.data!!)
            } catch (e: Exception) {
                _uploadPressure.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

}