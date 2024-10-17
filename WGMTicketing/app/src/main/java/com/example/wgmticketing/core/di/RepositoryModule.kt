package com.example.wgmticketing.core.di

import com.example.wgmticketing.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}