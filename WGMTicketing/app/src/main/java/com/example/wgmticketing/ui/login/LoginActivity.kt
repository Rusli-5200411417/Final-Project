package com.example.wgmticketing.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.wgmticketing.NavigasiActivity
import com.example.wgmticketing.R
import com.example.wgmticketing.core.data.source.remote.network.State
import com.example.wgmticketing.core.data.source.remote.request.LoginRequest
import com.example.wgmticketing.databinding.ActivityLoginBinding
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityLoginBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val statusBarColor = ContextCompat.getColor(this, R.color.background)
        window.statusBarColor = statusBarColor

        if (SPrefs.isLogin) {
            // Pengguna sudah login sebelumnya, langsung arahkan ke NavigasiActivity
            pushActivity(NavigasiActivity::class.java)
            finish()
        }

        setData()
        mainButton()
    }

    private fun mainButton() {
        binding.btnMasuk.setOnClickListener {
            login()
        }

        binding.btnMoveDaftar.setOnClickListener {
            intentActivity(RegisterActivity::class.java)
        }
        binding.forgotPassword.setOnClickListener{
            intentActivity(ForgotPasswordActivity::class.java)
        }

    }

    private fun setData() {

        binding.btnMasuk.setOnClickListener {
            login()
        }

    }

    private fun login() {

        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )


        viewModel.login(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
//                    dismisLoading()
                    showToast("Selamat datang " + it.data?.name)
                    SPrefs.isLogin  =   true
                    SPrefs.setUser(it.data)
                    pushActivity(NavigasiActivity::class.java)
                    finish()
                }
                State.ERROR -> {
//                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
//                    showLoading()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }
}

//    fun testing() {
//        val s   =   SPrefs(this)
//        if(s.getIsLogin())  {
//            binding.tvDaftar.text = "SUDAH LOGIN"
//        } else
//            binding.tvDaftar.text =   "BELUM LOGIN"
//
//        binding.btnMasuk.setOnClickListener {
//            s.setIsLogin(true)
//        }
//
//        binding.btnMasuk.setOnClickListener {
//            s.setIsLogin(false)
//            onBackPressed()
//        }
//
//        Log.d("RESPON", "PESAN SINGKAT")
//    }

