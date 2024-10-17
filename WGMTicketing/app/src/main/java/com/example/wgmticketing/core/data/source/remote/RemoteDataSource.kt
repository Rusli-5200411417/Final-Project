package com.example.wgmticketing.core.data.source.remote

import com.example.wgmticketing.core.data.source.remote.network.ApiService
import com.example.wgmticketing.core.data.source.remote.request.ForgotPasswordRequest
import com.example.wgmticketing.core.data.source.remote.request.LoginRequest
import com.example.wgmticketing.core.data.source.remote.request.OrderRequest
import com.example.wgmticketing.core.data.source.remote.request.RegisterRequest
import com.example.wgmticketing.core.data.source.remote.request.UpdateRequest
import okhttp3.MultipartBody


class RemoteDataSource(private val api: ApiService) {

    suspend fun login(data: LoginRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.register(data)
    suspend fun updateUser(data: UpdateRequest) = api.updateUser(data.id, data)
    suspend fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) =
        api.uploadUser(id, fileImage)

    suspend fun setForgetPassword(data: ForgotPasswordRequest) = api.forgotPassword(data)
    suspend fun getContent() = api.getContent()
    suspend fun getTicket(data: OrderRequest) = api.ticketPayment(data)
    suspend fun getTransaction(id: Int?) = api.getTransaction(id)
    suspend fun getDetail(id: Int?) = api.getDetail(id)
}