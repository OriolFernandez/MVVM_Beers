package com.uriolus.mvvbeers.di

import com.uriolus.mvvbeers.domain.usecase.GetAllBeersUseCase
import com.uriolus.mvvbeers.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {

    factory { GetAllBeersUseCase(get()) }
    viewModel {
        MainViewModel(
            getAllBeersUseCase = get()
        )
    }
}