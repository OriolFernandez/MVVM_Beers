package com.uriolus.mvvbeers.domain.model

sealed class GetBeerErrors {
    object BeerNotFound : GetBeerErrors()
}