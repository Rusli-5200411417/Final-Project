package com.example.wgmticketing.ui.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wgmticketing.core.data.source.remote.network.State
import com.example.wgmticketing.databinding.FragmentHomeBinding
import com.example.wgmticketing.ui.order.OrderActivity
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.logs
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel: HomeViewModel by viewModel()
    private val user = SPrefs.getUser()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        swipeRefreshLayout = binding.refresh
        swipeRefreshLayout.setOnRefreshListener {
            // Panggil fungsi ubuntu melakukan refresh data di sini
            refreshData()
        }

        binding.tvInisialProfil.text = user?.name.getInitial()
        binding.tvNamaProfil.text = user?.name

        val currentDateTime = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
            Date()
        )


        binding.tvDate.text = currentDateTime

        mainButton()
        getContent()
        return root
    }

    fun mainButton(){
        binding.bBuy.setOnClickListener{
            val intent = Intent(requireContext(), OrderActivity::class.java)
            startActivity(intent)
        }
    }


    private fun refreshData() {
        // Sebagai contoh, kita akan memanggil kembali fungsi setUser()
        getContent()
        // Selesai melakukan refresh, beritahu SwipeRefreshLayout bahwa proses refresh sudah selesai
        swipeRefreshLayout.isRefreshing = false
    }

    fun getContent(){
        viewModel.getContent().observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> {
                    val data = it.data

                    // Mengupdate UI dengan data yang diterima
                    data?.let {
                       SPrefs.setContent(data)
                        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(it.price)

                        // Update UI dengan data yang diterima
                        binding.apply {
                            content1.text = it.name?.uppercase()
                            content2.text = it.open_gate + " - "
                            content3.text = it.closed_gate
                            content4.text = formattedPrice
                            content5.text = it.description
                        }
                    }
                    logs("content", "$data")
                // Simpan harga ke SPrefs


                }
                State.LOADING -> {
                    // Tindakan apa pun yang perlu dilakukan saat status adalah LOADING
                }
                State.ERROR -> {
                    // Mengupdate UI dengan pesan kesalahan jika ada
                    binding.apply {
                        content1.text = "data not found"
                        content2.text = "data not found"
                        content3.text = "data not found"
                        content4.text = "data not found"
                        content5.text = "data not found"
                    }
                }
            }
        }

    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
