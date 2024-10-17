package com.example.wgmticketing.core.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val id : String,
    val id_order: String,
    val booking_code: String,
    val id_user: Int,
    val name: String,
    val email: String,
    val total_ticket: Int,
    val total_amount: Int,
    val va: String,
    val bank: String,
    val status: String,
    val created_at: String,
    val updated_at: String,
): Parcelable
