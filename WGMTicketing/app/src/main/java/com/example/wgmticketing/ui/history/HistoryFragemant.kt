package com.example.wgmticketing.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wgmticketing.core.data.source.remote.network.State
import com.example.wgmticketing.databinding.FragmentHistoryFragemantBinding
import com.example.wgmticketing.ui.history.adapter.HistoryAdapter
import com.example.wgmticketing.util.SPrefs
import com.inyongtisto.myhelper.extension.logs
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragemant : Fragment() {

    private var _binding: FragmentHistoryFragemantBinding? = null
    private val binding get() = _binding!!
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val userId = SPrefs.getUserId()
    private val viewModel: HistoryViewModel by viewModel()
    private val adapter = HistoryAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryFragemantBinding.inflate(inflater,container,false)
        val root: View = binding.root

        swipeRefreshLayout = binding.refresh
        swipeRefreshLayout.setOnRefreshListener {
            // Panggil fungsi ubuntu melakukan refresh data di sini
            refreshData()
        }

        setupAdapter()
        getTransaction()
        return root
    }

    private fun refreshData() {
        // Sebagai contoh, kita akan memanggil kembali fungsi setUser()
        adapter.clearItems()
        getTransaction()

        // Selesai melakukan refresh, beritahu SwipeRefreshLayout bahwa proses refresh sudah selesai
        swipeRefreshLayout.isRefreshing = false
    }


    private fun setupAdapter() {
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = adapter
    }

    fun getTransaction() {
        viewModel.getTransaction(userId).observe(viewLifecycleOwner) { response ->
            when (response.state) {
                State.SUCCESS -> {
                    val res = response.data
                    if (res != null) {
                        // Check the status field in the NewsResponse
                        if (res.status == "ok") {
                            // The API request was successful and the status is "ok"
                                val items = res.data
                                adapter.addItems(items)
                                adapter.notifyDataSetChanged()
                            logs ("Api", "get data succes $items")
                        } else {
                            // Handle the case where the API response status is not "ok"
                            logs("Api", "API request failed with status: error")
                        }
                    } else {
                        // Handle the case where the API response data is null
                        logs("Api", "API response data is null")
                    }
                }
                State.ERROR -> {
                    // Handle the error, show a message to the user, or log it
                    logs("Api", "API request failed")
                }
                State.LOADING -> {
                    // You can handle loading state here, e.g., show a progress indicator
                }
            }
        }
    }
}
