package com.example.wgmticketing.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.wgmticketing.core.data.repository.AppRepository

class HistoryViewModel(private val repo: AppRepository): ViewModel() {

    fun getTransaction(id: Int?) = repo.getTransaction(id).asLiveData()
    fun getDetail(id: Int?) = repo.getDetail(id).asLiveData()
}