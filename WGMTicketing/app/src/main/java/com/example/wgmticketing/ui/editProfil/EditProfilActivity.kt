package com.example.wgmticketing.ui.editProfil

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wgmticketing.NavigasiActivity
import com.example.wgmticketing.core.data.source.remote.network.State
import com.example.wgmticketing.core.data.source.remote.request.UpdateRequest
import com.example.wgmticketing.databinding.ActivityEditProfilBinding
import com.example.wgmticketing.ui.base.MyActivity
import com.example.wgmticketing.ui.login.AuthViewModel
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

@Suppress("DEPRECATION")
class EditProfilActivity : MyActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityEditProfilBinding?  =   null
    private val binding get()   =   _binding!!
    private var fileImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setToolbar(binding.imgBack, "Edit Personal Info")

        mainButton()
        setData()

    }

    private fun setData()   {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtGender.setText(user.jenis_kelamin)
                edtAge.setText(user.usia)
                tvInisialProfil.text    =   user.name.getInitial()

            }
        }
    }

    private fun mainButton() {

        binding.btnSave.setOnClickListener {
            if (fileImage != null) {
                upload()
            } else {
                update()
            }
        }
//        binding.btnEditpp.setOnClickListener {
//            picImage()
//        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!

            //use the url to load the image
            fileImage   =   File(uri.path!!)
            Picasso.get().load(fileImage!!).into(binding.imgProfil)
        }
    }

    private fun update() {

        if (binding.edtName.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtGender.isEmpty()) return
        if (binding.edtAge.isEmpty()) return


        val idUser = SPrefs.getUser()?.id
        val body = UpdateRequest(
            idUser.int(), // jika tidak menggunakna library idUser ?: 0,
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtAge.text.toString(),
            binding.edtGender.text.toString(),
        )

        viewModel.updateUser(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    showToast("Update Profil Sukses " + it.data?.name)
                    pushActivity(NavigasiActivity::class.java)
                }
                State.ERROR -> {
                    progress.dismiss()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    progress.show()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }

    private fun upload() {
        val idUser = SPrefs.getUser()?.id
        val file = fileImage.toMultipartBody("foto_profil") //ditambahkan foto_profil karena agar sesuai kolom dtbasenya

        viewModel.uploadUser(idUser, file).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    update()

                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    progress.show()
                }
            }

//            showToast("Selamat datang  " + it?.data?.nama)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}