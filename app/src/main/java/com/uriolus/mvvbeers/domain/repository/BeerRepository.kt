package com.uriolus.mvvbeers.domain.repository

import com.uriolus.mvvbeers.domain.model.*
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getAllBeers(): Flow<List<Beer>>
}