package com.example.wgmticketing.ui.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wgmticketing.NavigasiActivity
import com.example.wgmticketing.core.data.source.remote.network.State
import com.example.wgmticketing.core.data.source.remote.request.OrderRequest
import com.example.wgmticketing.databinding.ActivitySecondOrderBinding
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale

private var _binding: ActivitySecondOrderBinding?  =   null
private val binding get()   =   _binding!!

class SecondOrderActivity : AppCompatActivity() {
    private val viewModel: OrderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = SPrefs.getUser()
        val name = user?.name ?: ""
        binding.name.text = name

        // Mendapatkan nilai harga dari SPrefs.getContent()
        val price = SPrefs.getPrice()
        val formattedPrice1 = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(price)

        binding.price.text =formattedPrice1

        // Mendapatkan nilai total ticket dari Intent sebelumnya
        val totalTicket = intent.getIntExtra("TOTAL_AMOUNT", 0)
        binding.totalTicket.text = totalTicket.toString()

        val bank = intent.getStringExtra("SELECTED_BANK")
        binding.bank.text = bank

        // Menghitung total dengan perkalian harga dan total ticket
        val total = price * totalTicket
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(total)

        binding.totalAmount.text = formattedPrice

        mainButton()

    }

    fun mainButton(){
        binding.buy.setOnClickListener{
            Order()
        }

        binding.batal.setOnClickListener{
            pushActivity(NavigasiActivity::class.java)
        }
    }

    private fun Order() {

        val user = SPrefs.getUser()
        val userId = user?.id
        val total = binding.totalTicket.text.toString().toIntOrNull() ?: 0
        val bank = binding.bank.text.toString()

        val body = OrderRequest(
            userId,
            total,
            bank
        )

        viewModel.getTicket(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
//                    dismisLoading()
                    showToast("data berhasil ")
                    pushActivity(NavigasiActivity::class.java) //push ke halaman LoginActivity
                }

                State.ERROR -> {
//                    dismisLoading()
                    toastError(it.message ?: "Error")

                }

                State.LOADING -> {
//                    showLoading()
                }
            }

        }
    }
}