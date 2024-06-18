package com.example.capstoneproject.ui.feature.item.infopenyakit.detailpenyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.DataDetail
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class DetailPenyakitViewModel(private val repository: UserRepository) : ViewModel() {

    private val _diseases = MutableLiveData<Result<DataDetail>>()
    val diseases: LiveData<Result<DataDetail>> get() = _diseases

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getDiseaseDetail(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                println("Fetching disease detail for id: $id")
                val response = repository.getDiseaseDetail(id)
                _diseases.value = Result.success(response.data!!)
                println("Disease Detail Loaded: $_diseases.value")
            } catch (e: Exception) {
                _diseases.value = Result.failure(e)
                println("Error Loading Disease Detail: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}