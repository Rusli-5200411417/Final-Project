package com.example.wgmticketing.core.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wgmticketing.core.data.repository.AppRepository

class HomeViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModelFactory::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModelFactory(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
