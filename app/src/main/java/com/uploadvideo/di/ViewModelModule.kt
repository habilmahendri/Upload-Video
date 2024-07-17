package com.uploadvideo.di

import com.uploadvideo.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule  = module {
    viewModel { HomeViewModel(get()) }
}