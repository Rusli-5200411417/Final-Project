package com.example.wgmticketing.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wgmticketing.databinding.ActivityProfilBinding
import com.example.wgmticketing.ui.editProfil.EditProfilActivity
import com.example.wgmticketing.ui.login.LoginActivity
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.pushActivity

class ProfilActivity : Fragment() {

//    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityProfilBinding?  =   null
    private val binding get()   =   _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityProfilBinding.inflate(inflater,container,false)
        val root: View = binding.root

       

        mainButton()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }


    private fun mainButton() {
        binding.btnKeluar.setOnClickListener {
            SPrefs.isLogin  =   false
            pushActivity(LoginActivity::class.java)
        }

        binding.imgEdit.setOnClickListener {
            pushActivity(EditProfilActivity::class.java)
        }

    }

    private fun setUser()   {
        val user = SPrefs.getUser()
        if (user != null) {
            binding.apply {
                tvNamaProfil.text = user.name
                tvName.text = user.name
                idUser.text  = "Nomor ID : " + user.id_user
                tvEmail.setText(user.email)
                tvAge.setText(user.usia) ?: "*lengkapi data usia"
                tvGender.setText(user.jenis_kelamin) ?: "*Lengkapi data jenis kelamin"
                tvInisialProfil.text    =   user.name.getInitial()
            }
        }

    }
}
