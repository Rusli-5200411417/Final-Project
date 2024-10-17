package com.example.wgmticketing.core.data.source.remote.request

data class UpdateRequest(
    val id: Int,
    val name:   String? = null,
    val email:      String? = null,
    val usia: String? = null,
    val jenis_kelamin: String? = null,
)