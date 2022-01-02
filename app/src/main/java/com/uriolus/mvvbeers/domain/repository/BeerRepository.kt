package com.uriolus.mvvbeers.domain.repository

import com.uriolus.mvvbeers.data.api.model.ApiStatus
import com.uriolus.mvvbeers.domain.model.*
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getAllBeers(): Flow<ApiStatus<List<Beer>>>
}