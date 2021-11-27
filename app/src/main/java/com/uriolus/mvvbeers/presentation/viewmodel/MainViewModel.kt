package com.uriolus.mvvbeers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uriolus.mvvbeers.domain.usecase.GetAllBeersUseCase
import com.uriolus.mvvbeers.presentation.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : ViewModel() {
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    fun getMainStateFlow() = _mainState.asStateFlow()

    fun requestAllBeers() {
        _mainState.value = MainState.Loading
        viewModelScope.launch {
            getAllBeersUseCase.exec()
                .collect { beers ->
                    if (beers.isEmpty()) {
                        _mainState.value = MainState.Empty
                    } else {
                        _mainState.value = MainState.Loaded(beers)
                    }
                }
        }
    }
}
