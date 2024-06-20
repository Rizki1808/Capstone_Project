package com.example.capstoneproject.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject.data.response.NewsResponse
import com.example.capstoneproject.data.tools.UserRepository
import kotlinx.coroutines.launch

class ExploreViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _news = MutableLiveData<Result<NewsResponse>>()
    val news: LiveData<Result<NewsResponse>> get() = _news

    fun getNews(page: Int = 1, pageSize: Int = 20) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = userRepository.getTopHeadlines(page, pageSize)
                _news.value = Result.success(result)
            } catch (e: Exception) {
                _news.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}