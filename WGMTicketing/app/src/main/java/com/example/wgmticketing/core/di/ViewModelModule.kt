package com.example.wgmticketing.core.di

import com.example.wgmticketing.ui.history.HistoryViewModel
import com.example.wgmticketing.ui.home.HomeViewModel
import com.example.wgmticketing.ui.login.AuthViewModel
import com.example.wgmticketing.ui.order.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { OrderViewModel (get())}
    viewModel { HistoryViewModel(get()) }
    }
