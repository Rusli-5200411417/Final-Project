package com.example.wgmticketing.core.data.source.remote.response

import com.example.wgmticketing.core.data.source.model.ForgotPassword

data class ForgotPasswordResponse (
   val code :  Int? = null,
   val message:    String? = null,
   val data: ForgotPassword?  =   null
)
