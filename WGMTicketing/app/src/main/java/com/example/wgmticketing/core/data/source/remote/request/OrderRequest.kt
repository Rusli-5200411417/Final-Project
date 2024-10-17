package com.example.wgmticketing.core.data.source.remote.request

data class OrderRequest(
    val user_id: Int? ,
    val total: Int,
    val bank: String
)
