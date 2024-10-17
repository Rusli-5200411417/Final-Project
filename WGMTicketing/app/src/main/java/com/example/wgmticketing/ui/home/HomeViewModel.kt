package com.example.wgmticketing.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.wgmticketing.core.data.repository.AppRepository

class HomeViewModel(private val repo: AppRepository): ViewModel() {

    fun getContent() = repo.getContent().asLiveData()
}