package com.example.wgmticketing.core.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val created_at: String?,
    val email: String?,
    val id_user: String?,
    val id: Int?,
    val name: String?,
    val role: String?,
    val updated_at: String?,
    val jenis_kelamin: String?,
    val usia: String?,
): Parcelable
