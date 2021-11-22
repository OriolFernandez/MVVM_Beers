package com.uriolus.mvvbeers.data.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.model.BeerId
import com.uriolus.mvvbeers.domain.model.GetBeerErrors
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class BeerDataSourceMemory : BeerDataSource {
    private val list: MutableList<Beer> = mutableListOf()
    private val _sharedFlow = MutableSharedFlow<List<Beer>>()

    override suspend fun getBeer(beerId: BeerId): Either<GetBeerErrors, Beer> {
        return list.firstOrNull { it.id == beerId }?.right() ?: GetBeerErrors.BeerNotFound.left()
    }

    override fun getAllBeersFlow(): SharedFlow<List<Beer>> {
        return _sharedFlow
    }
}
