package com.example.wgmticketing.core.data.repository

import com.example.wgmticketing.core.data.source.local.LocalDataSource
import com.example.wgmticketing.core.data.source.remote.RemoteDataSource
import com.example.wgmticketing.core.data.source.remote.network.Resource
import com.example.wgmticketing.core.data.source.remote.request.ForgotPasswordRequest
import com.example.wgmticketing.core.data.source.remote.request.LoginRequest
import com.example.wgmticketing.core.data.source.remote.request.OrderRequest
import com.example.wgmticketing.core.data.source.remote.request.RegisterRequest
import com.example.wgmticketing.core.data.source.remote.request.UpdateRequest
import com.example.wgmticketing.core.data.source.remote.response.Order2Response
import com.example.wgmticketing.core.data.source.remote.response.OrderDetailResponse
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null,))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    SPrefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    logs("user:" + user.toJson())
                    SPrefs.setUser(user)
                    emit(Resource.success(user))
                    logs("success" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error Default", null))
                    logs("Error: " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }


    fun register(data: RegisterRequest) =   flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful) {
                    SPrefs.isLogin  = true //jika mau ke login dulu ini bisa dihapus
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user) //ini juga -> lanjut ke RegisterActivity
                    emit(Resource.success(user))
                    logs("success" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                    logs("Error: " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun updateUser(data: UpdateRequest) =   flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful) {
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user) //untuk update data user terbaru
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) =   flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful) {
                    val body    =   it.body()
                    val user    = body?.data
                    SPrefs.setUser(user) //untuk update data user terbaru
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun setForgotPassword(data: ForgotPasswordRequest) =   flow {
        emit(Resource.loading(null))
        try {
            remote.setForgetPassword(data).let {
                if (it.isSuccessful) {
                    val body    =   it.body()
                    val data    = body?.data
                    emit(Resource.success(data))
                    logs("forgot-password","$data")
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun getContent() = flow{
        try {
            val response = remote.getContent()
            if (response.isSuccessful) {
                val body = response.body()
                val contentList = body?.data

                if (contentList != null) {
                    // Emit Resource.success dengan data yang berhasil diterima
                    logs("content", "data: $contentList")
                    emit(Resource.success(contentList))
                } else {
                    logs("content", "response error 0")
                    emit(Resource.error("Response body is empty", null))
                }
            } else {
                // Jika respons tidak berhasil, kirim Resource.error dengan pesan kesalahan yang sesuai
                emit(Resource.error(response.errorBody()?.string() ?: "Error Default", null))
                logs("content", "response error 1")
            }
        } catch (e: Exception) {
            val errorMessage = "Network Error: ${e.message}"
            emit(Resource.error(errorMessage, null))
            logs("content", errorMessage)
        }
    }

    fun getTicket(data: OrderRequest) =   flow {
        emit(Resource.loading(null))
        try {
            remote.getTicket(data).let {
                if (it.isSuccessful) {
                    val body    =   it.body()
                    val ticket    = body?.va
                    emit(Resource.success(ticket))
                    logs("test" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message
                        ?: "Error Default", null))
                    logs("test: " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("test:" + e.message)
        }
    }

    fun getTransaction(id: Int?): Flow<Resource<Order2Response>> = flow {
        id ?: run {
            emit(Resource.error("ID cannot be null", null))
            return@flow
        }

        emit(Resource.loading(null))

        try {
            remote.getTransaction(id).let { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(Resource.success(body))
                        logs("Transaction retrieved successfully: $body")
                    } else {
                        emit(Resource.error("Data is null", null))
                        logs("Transaction data is null")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error Default"
                    emit(Resource.error(errorMessage, null))
                    logs("Error retrieving transaction: $errorMessage")
                }
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Terjadi Kesalahan"
            emit(Resource.error(errorMessage, null))
            logs("Error retrieving transaction: $errorMessage")
        }
    }

    fun getDetail(id: Int?): Flow<Resource<OrderDetailResponse>> = flow {
        if (id == null) {
            emit(Resource.error("ID cannot be null", null))
            return@flow
        }

        emit(Resource.loading(null))

        val response = try {
            remote.getDetail(id)
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            return@flow
        }

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Resource.success(body))
                logs("Transaction retrieved successfully: $body")
            } else {
                emit(Resource.error("Data is null", null))
                logs("Transaction data is null")
            }
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Error Default"
            emit(Resource.error(errorMessage, null))
            logs("Error retrieving transaction: $errorMessage")
        }
    }.catch { e ->
        emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        logs("Error retrieving transaction: ${e.message}")
    }



}