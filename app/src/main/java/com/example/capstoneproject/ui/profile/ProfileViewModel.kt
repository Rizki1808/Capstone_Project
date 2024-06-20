package com.example.capstoneproject.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.DataProfile
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _profile = MutableLiveData<Result<DataProfile>>()
    val profile: LiveData<Result<DataProfile>> get() = _profile

    fun getProfile() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getProfile()
                _profile.value = Result.success(response.data!!)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.logout()
            _isLoading.value = false
        }
    }
}