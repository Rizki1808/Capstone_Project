package com.example.capstoneproject.ui.feature.item.infopenyakit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.capstoneproject.data.api.ApiService
import com.example.capstoneproject.data.response.ListStoryItem

class InfoPenyakitPagingSource(
    private val apiService: ApiService,
    private val token: String
) : PagingSource<Int, ListStoryItem>() {

    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListStoryItem> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getStories(position, params.loadSize)
            LoadResult.Page(
                data = response.listStory,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.listStory.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}