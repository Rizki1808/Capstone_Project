package com.example.capstoneproject.ui.feature.item.diari.guladarah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.SugarResponse
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class GulaViewModel(private val userRepository: UserRepository) : ViewModel()  {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _sugar = MutableLiveData<Result<SugarResponse>>()
    val sugar: LiveData<Result<SugarResponse>> get() = _sugar

    fun getSugar() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = userRepository.getSugarBlood()
                val sortedData = result.data.sortedWith(compareByDescending {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("${it.checkDate} ${it.checkTime}")
                })
                _sugar.value = Result.success(SugarResponse(sortedData))
            } catch (e: Exception) {
                _sugar.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}