package com.uriolus.mvvbeers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.usecase.*
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : ViewModel() {
    fun getAllBeersFlow(): Flow<List<Beer>> = getAllBeersUseCase.exec()
}