package com.uriolus.mvvbeers.presentation.compose

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import com.uriolus.mvvbeers.domain.model.Beer
import com.uriolus.mvvbeers.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val beersList: State<List<Beer>> = viewModel.beersFlow.collectAsState(listOf())
    Scaffold(
        content = {
            val list: List<Beer> = beersList.value
            if(list.isNotEmpty()) {
               BeerList(list)
            }
        }
    )
}