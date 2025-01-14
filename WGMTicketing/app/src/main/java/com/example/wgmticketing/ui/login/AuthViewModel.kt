package com.example.wgmticketing.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.wgmticketing.core.data.repository.AppRepository
import com.example.wgmticketing.core.data.source.remote.request.ForgotPasswordRequest
import com.example.wgmticketing.core.data.source.remote.request.LoginRequest
import com.example.wgmticketing.core.data.source.remote.request.RegisterRequest
import com.example.wgmticketing.core.data.source.remote.request.UpdateRequest
import okhttp3.MultipartBody

class AuthViewModel(val repo: AppRepository): ViewModel() {

    fun login(data: LoginRequest) =   repo.login(data).asLiveData()
    fun register(data: RegisterRequest) =   repo.register(data).asLiveData()
    fun updateUser(data: UpdateRequest) =   repo.updateUser(data).asLiveData()
    fun forgotPassword(data: ForgotPasswordRequest) =   repo.setForgotPassword(data).asLiveData()
    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) =   repo.uploadUser(id, fileImage).asLiveData()

}