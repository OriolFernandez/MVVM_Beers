package com.uriolus.mvvbeers.domain.usecase

import com.uriolus.mvvbeers.data.api.model.ApiStatus
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class GetAllBeersUseCase(private val repository: BeerRepository) {
    fun exec(): Flow<ApiStatus<List<Beer>>> =
        repository.getAllBeers()
}