package com.example.wgmticketing.core.data.source.remote.response

import com.example.wgmticketing.core.data.source.model.Order

data class OrderDetailResponse(
    val status: String,
    val message: String,
    val data:  Order
)
