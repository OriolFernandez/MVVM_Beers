package com.uriolus.mvvbeers.domain.model

sealed class GetAllBeersError {
    object ListEmpty : GetAllBeersError()
}