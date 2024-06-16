package com.example.capstoneproject.ui.feature.item.infopenyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.capstoneproject.data.UserRepository
import com.example.capstoneproject.data.response.ListStoryItem
import com.example.capstoneproject.data.response.UserModel
import kotlinx.coroutines.launch

class InfoPenyakitViewModel(private val repository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _stories = MutableLiveData<PagingData<ListStoryItem>>()
    val stories: LiveData<PagingData<ListStoryItem>> get() = _stories

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    init {
        viewModelScope.launch {
            repository.getStoriesPaging().cachedIn(viewModelScope).collect {
                _stories.postValue(it)
            }
        }
    }
}