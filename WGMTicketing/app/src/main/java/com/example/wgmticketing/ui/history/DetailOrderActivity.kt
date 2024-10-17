package com.example.wgmticketing.ui.history

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wgmticketing.core.data.source.remote.network.State
import com.example.wgmticketing.databinding.ActivityDetailOrderBinding
import com.inyongtisto.myhelper.extension.logs
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class DetailOrderActivity : AppCompatActivity() {
    private var _binding: ActivityDetailOrderBinding?  =   null
    private val binding get()   =   _binding!!
    private val viewModel: HistoryViewModel by viewModel()
    private var itemId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemId =intent.getIntExtra("item_id", 0)

        mainButton()
        getDetail()

//        binding.idDetail.text = itemId.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun mainButton() {

        // Tambahkan OnClickListener ke tombol copy
        binding.copy.setOnClickListener {
            // Ambil nilai dari binding.va.text
            val textToCopy = binding.va.text.toString()

            // Dapatkan ClipboardManager
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            // Buat ClipData dengan teks yang akan disalin
            val clip = ClipData.newPlainText("Virtual Account", textToCopy)

            // Salin teks ke clipboard
            clipboard.setPrimaryClip(clip)

            // Tampilkan pesan bahwa teks telah disalin
            Toast.makeText(this, "Virtual Account copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }


    fun getDetail() {
        viewModel.getDetail(itemId).observe(this) { response ->
            when (response.state) {
                State.SUCCESS -> {
                    val res = response.data
                    res?.let {
                        if (it.status == "ok") {
                            val items = it.data

                            val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                            val targetFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())

                            // Ensure created_at is not null before parsing
                            val createdAt = items.created_at
                            val formattedDateUnpaid = if (createdAt != null) {
                                val dateFormat = originalFormat.parse(createdAt)
                                targetFormat.format(dateFormat)
                            } else {
                                "Unknown Date"
                            }

                            // Ensure updated_at is not null before parsing
                            val updatedAt = items.updated_at
                            val formattedDatePaid = if (updatedAt != null) {
                                val dateFormatPaid = originalFormat.parse(updatedAt)
                                targetFormat.format(dateFormatPaid)
                            } else {
                                "Unknown Date"
                            }

                            val formattedPrice = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(items.total_amount)

                            binding.apply {
                                orderId.text = items.id_order
                                totalTicket.text = items.total_ticket.toString()
                                totalGross.text = formattedPrice

                                val cekStatus = items.status
                                status.text = cekStatus

                                if (cekStatus == "paid") {
                                    va.text = ""
                                    labelVa.text = ""
                                    labelDate.text = "Tanggal Pembayaran: "
                                    date.text = formattedDatePaid
                                    binding.copy.visibility = View.GONE
                                } else if (cekStatus == "unpaid") {
                                    va.text = items.va
                                    date.text = formattedDateUnpaid
                                    binding.copy.visibility = View.VISIBLE
                                }

                                val statusColor = when (cekStatus.toLowerCase(Locale.getDefault())) {
                                    "unpaid" -> Color.RED
                                    "paid" -> Color.GREEN
                                    else -> Color.BLACK // Default color
                                }
                                status.setTextColor(statusColor)
                                bank.text = items.bank
                            }
                            logs("Api", "get Data Success")
                        } else {
                            logs("Api", "data not Found")
                        }
                    } ?: run {
                        logs("Api", "get data Api error")
                    }
                }
                State.LOADING -> {
                    // Show loading indicator
                }
                State.ERROR -> {
                    logs("Api", "Api error to access")
                }
            }
        }
    }

}