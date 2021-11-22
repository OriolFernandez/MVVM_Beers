package com.uriolus.mvvbeers.data.repository

import com.uriolus.mvvbeers.data.datasource.BeerDataSource
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class BeerRepositoryDataSource(private val dataSource: BeerDataSource) : BeerRepository {
    override fun getAllBeers(): Flow<List<Beer>> {
        return dataSource.getAllBeersFlow()
    }
}