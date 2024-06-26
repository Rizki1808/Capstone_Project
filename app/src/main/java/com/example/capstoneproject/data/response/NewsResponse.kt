package com.example.capstoneproject.data.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)

data class Article(
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("content")
    val content: String?
) {
    companion object {
        fun createLoadingItem(): Article {
            return Article(
                source = Source("loading", "Loading"),
                author = null,
                title = "Loading...",
                description = null,
                url = "",
                urlToImage = null,
                publishedAt = "",
                content = null
            )
        }
    }
}

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)

