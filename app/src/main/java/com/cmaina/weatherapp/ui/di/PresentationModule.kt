package com.cmaina.weatherapp.ui.di

import com.cmaina.weatherapp.ui.screens.home.HomeViewModel
import com.cmaina.weatherapp.ui.screens.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
}