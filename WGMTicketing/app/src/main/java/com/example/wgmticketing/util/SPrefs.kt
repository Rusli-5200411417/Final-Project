package com.example.wgmticketing.util

import com.chibatching.kotpref.KotprefModel
import com.example.wgmticketing.core.data.source.model.Content
import com.example.wgmticketing.core.data.source.model.User
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object SPrefs : KotprefModel() {

    var isLogin by booleanPref(false)
    var user by stringPref()
    var content by stringPref() // Menggunakan intPref atau longPref tergantung pada kebutuhan



    fun setContent(data: Content?){
        content = data.toJson()
    }

    fun getContent(): Content? {
        if (content.isEmpty()) return null
        return content.toModel(Content::class.java)
    }

    fun setUser(data: User?) {
        user = data.toJson()
    }
    fun getPrice(): Int {
        val content = getContent()
        return content?.price ?: -1 // Mengembalikan -1 jika objek User atau ID tidak tersedia
    }


    fun getUser(): User? {
        if (user.isEmpty()) return null
        return user.toModel(User::class.java)
    }

//    fun getPrice(): Int{
//        val content = getContent()
//        return content?.price ?: -1
//    }
    fun getUserId(): Int {
        val user = getUser()
        return user?.id ?: -1 // Mengembalikan -1 jika objek User atau ID tidak tersedia
    }

//    fun setPrice(price: Int?) {
//        content = price.toString() ?: ""
//    }

}



