package com.example.burger.di

import com.example.burger.data.api.ServiceAPI
import com.example.burger.data.repository.BurgerRepository
import com.example.burger.domain.converter.BurgerConverter
import com.example.burger.ui.viewmodel.BurgerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
     // Retrofit
    factory<ServiceAPI> { get<Retrofit>().create(ServiceAPI::class.java) }

    // ViewModel
    viewModel { BurgerViewModel(get(), get()) }

    // Repository
    single { BurgerRepository(get()) }

    // Converter
    single { BurgerConverter() }
}
