package com.example.wgmticketing.core.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content (
    val id: Int?,
    val name: String?,
    val price: Int?,
    val open_gate: String?,
    val closed_gate: String?,
    val description: String?,
    val created_at: String?,
    val updated_at: String?
):Parcelable