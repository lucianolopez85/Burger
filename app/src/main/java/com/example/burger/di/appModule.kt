package com.example.burger.di

import androidx.navigation.NavController
import com.example.burger.data.api.ServiceAPI
import com.example.burger.data.repository.BurgerRepository
import com.example.burger.data.repository.BurgerRepositoryImpl
import com.example.burger.domain.converter.BurgerConverter
import com.example.burger.domain.usecase.BurgerUseCase
import com.example.burger.navigation.BurgerNavigation
import com.example.burger.ui.viewmodel.BurgerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    factory<ServiceAPI> { get<Retrofit>().create(ServiceAPI::class.java) }

    viewModel { BurgerViewModel(get()) }

    factory { BurgerUseCase(get(), get()) }

    factory<BurgerRepository> { BurgerRepositoryImpl(get()) }

    single { BurgerConverter() }

    factory { (navController: NavController) -> BurgerNavigation(navController) }
}
