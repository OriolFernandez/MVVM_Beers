package com.uriolus.mvvbeers.data.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.uriolus.mvvbeers.data.datasource.api.ApiService
import com.uriolus.mvvbeers.data.datasource.api.mapper.toBeer
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.model.BeerId
import com.uriolus.mvvbeers.domain.model.GetBeerErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BeerDataSourceApi(private val api: ApiService) : BeerDataSource {
    private val list: MutableList<Beer> = mutableListOf()

    override suspend fun getBeer(beerId: BeerId): Either<GetBeerErrors, Beer> {
        return list.firstOrNull { it.id == beerId }?.right() ?: GetBeerErrors.BeerNotFound.left()
    }

    override fun getAllBeersFlow(): Flow<List<Beer>> {
        return flow {
            emit(api.getBeers()
                .map { it.toBeer() } // TODO HANDLE EXCEPTION
            )
        }.flowOn(Dispatchers.IO)
    }
}
