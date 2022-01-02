package com.uriolus.mvvbeers.data.api.model

sealed class ApiStatus<out T> {
    object Loading: ApiStatus<Nothing>()
    class Loaded<T>(val value:T):ApiStatus<T>()
    class Error(val message:String):ApiStatus<Nothing>()
}