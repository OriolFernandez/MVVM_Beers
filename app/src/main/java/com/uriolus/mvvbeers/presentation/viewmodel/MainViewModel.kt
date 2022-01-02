package com.uriolus.mvvbeers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uriolus.mvvbeers.data.api.model.ApiStatus
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.usecase.GetAllBeersUseCase
import com.uriolus.mvvbeers.presentation.model.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : ViewModel() {
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    init {
        requestAllBeers()
    }

    val mainScreenState: StateFlow<MainState>
        get() = _mainState

    private fun requestAllBeers() {
        _mainState.value = MainState.Loading
        viewModelScope.launch {
            delay(1000) // For UI testing
            getAllBeersUseCase.exec()
                .collect { apiResult: ApiStatus<List<Beer>> ->
                    when (apiResult) {
                        is ApiStatus.Error -> {
                            _mainState.value = MainState.Error(apiResult.message)
                        }
                        is ApiStatus.Loaded -> {
                            if (apiResult.value.isEmpty()) {
                                _mainState.value = MainState.Empty
                            } else {
                                _mainState.value = MainState.Loaded(apiResult.value)
                            }
                        }
                        is ApiStatus.Loading -> _mainState.value = MainState.Loading
                    }
                }
        }
    }
}
