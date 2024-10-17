package com.example.wgmticketing.core.di

import com.example.wgmticketing.core.data.source.remote.network.ApiConfig
import com.example.wgmticketing.core.data.source.local.LocalDataSource
import com.example.wgmticketing.core.data.source.remote.RemoteDataSource
import org.koin.dsl.module


val appModule   =   module {
    single { ApiConfig.provideApiService }
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }
}