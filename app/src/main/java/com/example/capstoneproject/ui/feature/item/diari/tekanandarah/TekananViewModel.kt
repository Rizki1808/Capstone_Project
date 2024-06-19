package com.example.capstoneproject.ui.feature.item.diari.tekanandarah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.PressureResponse
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class TekananViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _pressure = MutableLiveData<Result<PressureResponse>>()
    val pressure: LiveData<Result<PressureResponse>> get() = _pressure

    fun getPressure() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = userRepository.getPressure()
                val sortedData = result.data.sortedWith(compareByDescending {
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse("${it.checkDate} ${it.checkTime}")
                })
                _pressure.value = Result.success(PressureResponse(sortedData))
            } catch (e: Exception) {
                _pressure.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}