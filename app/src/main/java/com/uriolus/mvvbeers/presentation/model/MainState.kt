package com.uriolus.mvvbeers.presentation.model

import com.uriolus.mvvbeers.domain.model.Beer

sealed class MainState {
    object Idle : MainState()
    object Empty : MainState()
    object Loading : MainState()
    class Loaded(val beers: List<Beer>) : MainState()
    class Error(val error: String) : MainState()
}