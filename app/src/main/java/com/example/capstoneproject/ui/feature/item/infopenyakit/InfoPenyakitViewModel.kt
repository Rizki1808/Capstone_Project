package com.example.capstoneproject.ui.feature.item.infopenyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.DiseasesResponse
import com.example.capstoneproject.data.tools.UserRepository
import com.example.capstoneproject.data.response.UserModel
import kotlinx.coroutines.launch

class InfoPenyakitViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _diseases = MutableLiveData<Result<DiseasesResponse>>()
    val diseases: LiveData<Result<DiseasesResponse>> get() = _diseases

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getDiseases() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = repository.getDiseases()
                _diseases.value = Result.success(result)
            } catch (e: Exception) {
                _diseases.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}