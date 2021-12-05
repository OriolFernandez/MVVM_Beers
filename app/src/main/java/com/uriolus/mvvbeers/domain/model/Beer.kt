package com.uriolus.mvvbeers.domain.model

data class Beer(
    val id: BeerId,
    val name: String,
    val imageUrl: String?,
    val description: String?
)

@JvmInline
value class BeerId( val s: String)

