package com.uriolus.mvvbeers.data.datasource.api.mapper

import com.uriolus.mvvbeers.data.datasource.api.model.BeerApiModel
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.domain.model.BeerId

fun BeerApiModel.toBeer(): Beer {
    return if (id != null && name != null ) {
        Beer(
            BeerId(id),
            name,
            imageUrl,
            description
        )
    } else {
        throw MappingNullFieldException()
    }
}

class MappingNullFieldException() : Throwable()