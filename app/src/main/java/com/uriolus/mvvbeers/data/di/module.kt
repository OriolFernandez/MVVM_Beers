package com.uriolus.mvvbeers.data.di

import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import com.uriolus.mvvbeers.data.datasource.BeerDataSource
import com.uriolus.mvvbeers.data.datasource.BeerDataSourceApi
import com.uriolus.mvvbeers.data.datasource.api.ApiService
import com.uriolus.mvvbeers.data.repository.BeerRepositoryDataSource
import com.uriolus.mvvbeers.domain.repository.BeerRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    //retrofit
    val baseURL = "https://api.punkapi.com/v2/"
    val gson = GsonBuilder().setLenient().create()
    val httpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        })
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    single<ApiService> {
        retrofit.create(ApiService::class.java)

    }

    single<BeerRepository> {
        val dataSource: BeerDataSource = BeerDataSourceApi(get())
        BeerRepositoryDataSource(dataSource)
    }
}