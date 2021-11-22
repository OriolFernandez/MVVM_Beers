package com.uriolus.mvvbeers.data.datasource.api

import com.uriolus.mvvbeers.data.datasource.api.model.BeerApiModel
import retrofit2.http.GET

interface ApiService {
    @GET("beers")
    suspend fun getBeers(): List<BeerApiModel>
}