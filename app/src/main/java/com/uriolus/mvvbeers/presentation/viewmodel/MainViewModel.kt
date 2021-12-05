package com.uriolus.mvvbeers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.usecase.GetAllBeersUseCase
import com.uriolus.mvvbeers.presentation.model.MainState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : ViewModel() {
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    val beersFlow: Flow<List<Beer>> =
        _mainState
            .filterIsInstance<MainState.Loaded>()
            .map { it.beers }

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
