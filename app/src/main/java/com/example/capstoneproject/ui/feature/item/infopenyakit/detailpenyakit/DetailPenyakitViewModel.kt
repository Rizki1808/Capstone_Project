package com.example.capstoneproject.ui.feature.item.infopenyakit.detailpenyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.DataItem
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class DetailPenyakitViewModel(private val repository: UserRepository) : ViewModel() {

    private val _diseases = MutableLiveData<Result<DataItem>>()
    val diseases: LiveData<Result<DataItem>> get() = _diseases

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getDiseaseDetail(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = repository.getDiseaseDetail(id)
                _diseases.value = Result.success(result.diseases!!)
            } catch (e: Exception) {
                _diseases.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}