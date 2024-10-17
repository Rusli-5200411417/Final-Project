package com.example.wgmticketing.ui.order

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.wgmticketing.NavigasiActivity
import com.example.wgmticketing.databinding.ActivityOrderBinding
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.pushActivity
import java.text.NumberFormat
import java.util.Locale

private var _binding: ActivityOrderBinding?  =   null
private val binding get()   =   _binding!!
private val spinnerOptions = arrayOf("bni", "bca")

class OrderActivity : AppCompatActivity() {
    private var contentPrice = SPrefs.getPrice()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(contentPrice)



        binding.orderConten1.text = formattedPrice

        val spinner = binding.spBank
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, spinnerOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        mainButton()
    }

    private fun mainButton(){
        binding.buyTicket.setOnClickListener{
            val selectedBank = binding.spBank.selectedItem as? String
            val total = binding.edTotal.text.toString().toIntOrNull() ?: 0

            val intent = Intent(this, SecondOrderActivity::class.java)
            intent.putExtra("SELECTED_BANK", selectedBank)
            intent.putExtra("TOTAL_AMOUNT", total)
            startActivity(intent)
        }

        binding.imgBack.setOnClickListener{
            pushActivity(NavigasiActivity::class.java)
        }
    }

}