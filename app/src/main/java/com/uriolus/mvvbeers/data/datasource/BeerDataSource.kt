package com.uriolus.mvvbeers.data.datasource

import arrow.core.Either
import com.uriolus.mvvbeers.data.api.model.ApiStatus
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.model.BeerId
import com.uriolus.mvvbeers.domain.model.GetBeerErrors
import kotlinx.coroutines.flow.Flow

interface BeerDataSource {
    suspend fun getBeer(beerId: BeerId): Either<GetBeerErrors, Beer>
    fun getAllBeersFlow(): Flow<ApiStatus<List<Beer>>>
}