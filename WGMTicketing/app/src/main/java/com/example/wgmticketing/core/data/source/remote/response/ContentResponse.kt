package com.example.wgmticketing.core.data.source.remote.response


import com.example.wgmticketing.core.data.source.model.Content

data class ContentResponse(
    val code: Int? = null,
    val message: String? = null,
    val data: Content
)
