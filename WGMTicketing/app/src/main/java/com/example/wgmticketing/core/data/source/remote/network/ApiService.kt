package com.example.wgmticketing.core.data.source.remote.network

import com.example.wgmticketing.core.data.source.remote.request.ForgotPasswordRequest
import com.example.wgmticketing.core.data.source.remote.request.LoginRequest
import com.example.wgmticketing.core.data.source.remote.request.OrderRequest
import com.example.wgmticketing.core.data.source.remote.request.RegisterRequest
import com.example.wgmticketing.core.data.source.remote.request.UpdateRequest
import com.example.wgmticketing.core.data.source.remote.response.ContentResponse
import com.example.wgmticketing.core.data.source.remote.response.ForgotPasswordResponse
import com.example.wgmticketing.core.data.source.remote.response.LoginResponse
import com.example.wgmticketing.core.data.source.remote.response.Order2Response
import com.example.wgmticketing.core.data.source.remote.response.OrderDetailResponse
import com.example.wgmticketing.core.data.source.remote.response.OrderResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //  @Header(API)
    @POST("login")
    suspend fun login(
        @Body login: LoginRequest,
    ): Response<LoginResponse>

    //  https://127.0.0.1:8000/api/register
    @POST("register")
    suspend fun register    (
        @Body data: RegisterRequest
    ):  Response<LoginResponse>

    @POST("forgot-password")
    suspend fun forgotPassword    (
        @Body data: ForgotPasswordRequest
    ):  Response<ForgotPasswordResponse>

    @PUT("update-user/{id}")
    suspend fun updateUser    (
        @Path("id") int: Int,
        @Body data: UpdateRequest
    ):  Response<LoginResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser    (
        @Path("id") int: Int? = null,
        @Body data: MultipartBody.Part? = null
    ):  Response<LoginResponse>

    @GET("content")
    suspend fun getContent(
    ): Response<ContentResponse>

    @POST("ticket/payment")
    suspend fun ticketPayment(
        @Body data:OrderRequest
    ): Response<OrderResponse>

    @GET("transaction/{id}")
    suspend fun getTransaction(
        @Path("id") int: Int? = null,
    ): Response<Order2Response>

    @GET("getDetail/{id}")
    suspend fun getDetail(
        @Path("id") int: Int?
    ): Response<OrderDetailResponse>
}