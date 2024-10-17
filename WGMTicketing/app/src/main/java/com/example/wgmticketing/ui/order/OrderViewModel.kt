package com.example.wgmticketing.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.wgmticketing.core.data.repository.AppRepository
import com.example.wgmticketing.core.data.source.remote.request.OrderRequest

class OrderViewModel(private val repo: AppRepository): ViewModel()  {
    fun getTicket(data: OrderRequest) = repo.getTicket(data).asLiveData()
}